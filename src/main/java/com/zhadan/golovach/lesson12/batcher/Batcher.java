package com.zhadan.golovach.lesson12.batcher;

/**
 * Created by andrewzhadan on 8/19/14.
 */

import groovyx.gpars.actor.Actor;
import groovyx.gpars.actor.DynamicDispatchActor;
import groovyx.gpars.actor.impl.MessageStream;

import java.util.ArrayList;
import java.util.List;

public class Batcher<ARG, RES> extends DynamicDispatchActor {
    // fixed parameters
    private final BatchProcessor<ARG, RES> processor;
    private final int maxBatchSize;
    private final long batchWaitTimeout;
    // current state
    private final List<ARG> argList = new ArrayList<>();
    private final List<MessageStream> replyToList = new ArrayList<>();
    private long generationId = 0;
    private Actor lastTimer;

    public Batcher(BatchProcessor<ARG, RES> processor, int maxBatchSize, long batchWaitTimeout) {
        this.processor = processor;
        this.maxBatchSize = maxBatchSize;
        this.batchWaitTimeout = batchWaitTimeout;
    }

    public void onMessage(final ARG elem) {
        argList.add(elem);
        replyToList.add(getSender());
        if (argList.size() == 1) {
            lastTimer = new Timer<>(batchWaitTimeout, ++generationId, this).start();
        } else if (argList.size() == maxBatchSize) {
            lastTimer.send("KILL");
            lastTimer = null;
            nextGeneration();
        }
    }

    public void onMessage(final long timeOutId) {
        if (generationId == timeOutId) {
            nextGeneration();
        }
    }

    private void nextGeneration() {
        new DynamicDispatchActor() {
            public void onMessage(final Work<ARG, RES> work) throws Exception {
                List<RES> resultList = work.batcher.onBatch(work.argList);
                for (int k = 0; k < resultList.size(); k++) {
                    work.replyToList.get(k).send(resultList.get(k));
                }
                terminate();
            }
        }.start().send(new Work<>(processor, new ArrayList<>(argList), new ArrayList<>(replyToList)));
        argList.clear();
        replyToList.clear();
        generationId = generationId + 1;
    }

    private static class Work<ARG, RES> {
        public final BatchProcessor<ARG, RES> batcher;
        public final List<ARG> argList;
        public final List<MessageStream> replyToList;

        public Work(BatchProcessor<ARG, RES> batcher, List<ARG> argList, List<MessageStream> replyToList) {
            this.batcher = batcher;
            this.argList = argList;
            this.replyToList = replyToList;
        }
    }
}
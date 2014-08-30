package com.zhadan.golovach.lesson12.batcher;

import groovyx.gpars.actor.Actor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

/**
 * Created by andrewzhadan on 8/19/14.
 */
public class BatcherDemo {
    public static final int BATCH_SIZE = 3;
    public static final long BATCH_TIMEOUT = 100;

    public static void main(String[] args) throws InterruptedException, IOException {
        Actor actor = new Batcher<>(new BatchProcessorImpl(), BATCH_SIZE, BATCH_TIMEOUT).start();

        ExecutorService exec = newCachedThreadPool();
        exec.submit(() -> {
            System.out.println((String) actor.sendAndWait(("A")));
            return null;
        });
        exec.submit(() -> {
            Thread.sleep(25);
            System.out.println((String) actor.sendAndWait(("B")));
            return null;
        });
        exec.submit(() -> {
            Thread.sleep(50);
            System.out.println((String) actor.sendAndWait(("C")));
            Thread.sleep(25);
            System.out.println((String) actor.sendAndWait(("D")));
            return null;
        });

        exec.shutdown();
    }
}

class BatchProcessorImpl implements BatchProcessor<String, String> {
    public List<String> onBatch(List<String> argList) {
        System.out.println("onBatch(" + argList + ")");
        List<String> result = new ArrayList<>(argList.size());
        for (String arg : argList) {
            result.add("res#" + arg);
        }
        return result;
    }
}
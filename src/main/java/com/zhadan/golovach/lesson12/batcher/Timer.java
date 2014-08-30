package com.zhadan.golovach.lesson12.batcher;

import groovyx.gpars.MessagingRunnable;
import groovyx.gpars.actor.Actor;
import groovyx.gpars.actor.DefaultActor;
import groovyx.gpars.actor.impl.MessageStream;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by andrewzhadan on 8/19/14.
 */
public class Timer<T> extends DefaultActor {
    private final long timeout;
    private final T timeoutMsg;
    private final MessageStream replyTo;

    public Timer(long timeout, T timeoutMsg, MessageStream replyTo) {
        this.timeout = timeout;
        this.timeoutMsg = timeoutMsg;
        this.replyTo = replyTo;
    }

    protected void act() {
        loop(() -> react(timeout, MILLISECONDS, new MessagingRunnable() {
            protected void doRun(Object argument) {
                if (Actor.TIMEOUT.equals(argument)) {
                    replyTo.send(timeoutMsg);
                }
                terminate();
            }
        }));
    }
}

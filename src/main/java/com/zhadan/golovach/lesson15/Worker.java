package com.zhadan.golovach.lesson15;

import akka.actor.UntypedActor;

/**
 * Created by andrewzhadan on 8/20/14.
 */
public class Worker extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            getSender().tell(((String) message).toUpperCase(), getSelf());
        } else {
            unhandled(message);
        }
    }
}

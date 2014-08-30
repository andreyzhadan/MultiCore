package com.zhadan.golovach.lesson15.hashmap;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Created by andrewzhadan on 8/21/14.
 */
public class Container extends UntypedActor {
    private final ActorRef[] buckets = new ActorRef[16];

    public Container() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = getContext().actorOf(Props.create(Bucket.class), "bucket-" + i);
        }
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        Object[] msgArr = (Object[]) msg;
        String command = (String) msgArr[0];
        String key = (String) msgArr[1];
        switch (command) {
            case "put":
            case "remove":
                buckets[key.hashCode() % 16].tell(msg, getSelf());
                break;
            case "get":
                Object[] nextGet = {"get", key, getSender()};
                buckets[key.hashCode() % 16].tell(nextGet, getSelf());
                break;
            case "get/result":
                String val = (String) msgArr[2];
                ActorRef originalSender = (ActorRef) msgArr[3];
                Object[] responseGet = {"get/result", key, val};
                originalSender.tell(responseGet, getSelf());
                break;
        }
    }
}

package com.zhadan.golovach.lesson15.hashmap;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrewzhadan on 8/21/14.
 */
public class Bucket extends UntypedActor {
    private Map<String, String> data = new HashMap<>();

    @Override
    public void onReceive(Object msg) throws Exception {
        Object[] msgArr = (Object[]) msg;
        String command = (String) msgArr[0];
        String key = (String) msgArr[1];
        switch (command) {
            case "put": // {"put", key, value}
                String val = (String) msgArr[2];
                data.put(key, val);
                break;
            case "get":// {"get", key, originalSender}
                ActorRef originalSender = (ActorRef) msgArr[2];
                Object[] getResult = {"get/result", key, data.get(key), originalSender};
                getSender().tell(getResult, getSelf());
                break;
            case "remove":// {"remove", key}
                data.remove(key);
                break;
        }
    }
}

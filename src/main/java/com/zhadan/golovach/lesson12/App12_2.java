package com.zhadan.golovach.lesson12;

import groovyx.gpars.actor.Actor;
import groovyx.gpars.actor.DynamicDispatchActor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrewzhadan on 8/18/14.
 */
public class App12_2 {
    public static void main(String[] args) throws InterruptedException, IOException {

        Actor actor = new DynamicDispatchActor() {
            private Map<String, String> map = new HashMap<>();

            public void onMessage(String key) throws InterruptedException { // get
//                System.out.println("Receive String: " + key);
                getSender().send(map.get(key));
            }

            public void onMessage(String[] keyValue) { // put
//                System.out.println("Receive Strings: " + keyValue[0] + " / " + keyValue[1]);
                map.put(keyValue[0], keyValue[1]);
            }
        }.start();
        System.out.println((String) actor.sendAndWait("0"));
        System.out.println((String) actor.sendAndWait("1"));
        actor.send(new String[]{"0", "Mike"});
        System.out.println((String) actor.sendAndWait("0"));
    }
}

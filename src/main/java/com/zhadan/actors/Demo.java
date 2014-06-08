package com.zhadan.actors;

import groovyx.gpars.actor.Actor;
import groovyx.gpars.actor.DynamicDispatchActor;

import java.io.IOException;

/**
 * Created by andrewzhadan on 5/1/14.
 */
public class Demo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Actor actor = new DynamicDispatchActor() {
            public void onMessage(String msg) {
                System.out.println("ping: " + msg);
//                getSender().send(msg.toUpperCase());
            }
        }.start();

        actor.send("pong: "+actor.sendAndWait("Hello!"));
    }
}

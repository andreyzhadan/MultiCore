package com.zhadan.golovach.lesson12;

import groovyx.gpars.MessagingRunnable;
import groovyx.gpars.actor.Actor;
import groovyx.gpars.actor.DynamicDispatchActor;

/**
 * Created by andrewzhadan on 8/18/14.
 */
public class App12_1 {
    public static void main(String[] args) throws Exception {
//        DynamicDispatchActor -> stateless actor
        Actor actor = new DynamicDispatchActor() {

            public void onMessage(String msg) throws InterruptedException {
                System.out.println("Receive String: " + msg);
                Thread.sleep(1000);
                getSender().send(msg.toUpperCase());
            }

            public void onMessage(Integer msg) throws InterruptedException {
                System.out.println("Receive Integer: " + msg);
                Thread.sleep(500);
            }
        }.start();
        //SYNC
//        System.out.println("Callback " + actor.sendAndWait("Hello!"));

        //ASYNC
//        actor.send(2);
//        actor.send(2);
//        actor.send(2);
//        actor.send(2);


        //ASYNC with callback
        actor.sendAndContinue("Hello!", new MessagingRunnable<String>() {
            @Override
            protected void doRun(String msg) {
                System.out.println("pong: " + msg);
            }
        });

        System.out.println("END");
        System.in.read();
    }
}

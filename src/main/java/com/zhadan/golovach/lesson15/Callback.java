package com.zhadan.golovach.lesson15;

import akka.actor.UntypedActor;

import java.util.Arrays;

/**
 * Created by andrewzhadan on 8/19/14.
 */
public class Callback extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Object[]) {
            System.out.println("result: " + Arrays.toString((Object[]) message));
        } else {
            System.out.println("result: " + message + " from " + getSender());
        }
    }
}

package com.zhadan.golovach.lesson15.sum;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.zhadan.golovach.lesson15.Callback;

/**
 * Created by andrewzhadan on 8/21/14.
 */
public class SumApp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");
        ActorRef kernel = system.actorOf(Props.create(SumKernel.class, callback), "sumKernel");

        kernel.tell(new int[]{0, 20}, ActorRef.noSender());
    }
}

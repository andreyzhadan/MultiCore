package com.zhadan.golovach.lesson15;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.SmallestMailboxPool;

import java.util.Scanner;

/**
 * Created by andrewzhadan on 8/19/14.
 */
public class App15Router {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");

        // 5 actors of type Worker
        ActorRef router = system.actorOf(new SmallestMailboxPool(5).props(Props.create(Worker.class)), "worker");

        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if ("exit".equals(line)) {
                system.shutdown();
                return;
            }
            router.tell(line, callback);
        }
    }
}

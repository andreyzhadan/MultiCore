package com.zhadan.golovach.lesson15.hashmap;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.zhadan.golovach.lesson15.Callback;

import java.io.IOException;

/**
 * Created by andrewzhadan on 8/21/14.
 */
public class App {
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef container = system.actorOf(Props.create(Container.class), "container");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

        container.tell(msg("put", "keyA", "valueA"), ActorRef.noSender());
        container.tell(msg("put", "keyB", "valueB"), ActorRef.noSender());
        container.tell(msg("put", "keyC", "valueC"), ActorRef.noSender());

        container.tell(msg("remove", "keyB"), ActorRef.noSender());

        container.tell(msg("get", "keyA"), callback);
        container.tell(msg("get", "keyB"), callback);
        container.tell(msg("get", "keyC"), callback);

        System.in.read();
        system.shutdown();
    }

    private static Object msg(Object... elems) {
        return elems;
    }
}



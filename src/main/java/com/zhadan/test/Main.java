package com.zhadan.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by andrewzhadan on 5/1/14.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
//        A a = new A(obj);
//        B b = new B(obj);
//        a.doJob();
//        b.doJob();

//        synchronized (obj) {
//            synchronized (obj) {
//                System.out.println("right");
//            }
//        }

//        final StaticTest staticTest = new StaticTest();
        new Thread(StaticTest::f).start();

        new Thread(StaticTest::g).start();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Object> submit = executorService.submit(() -> "q");

        List<Long> list =new ArrayList<>(10_000_000);
        list.stream().parallel();


        AtomicInteger at = new AtomicInteger(0);
        at.incrementAndGet();
    }
}

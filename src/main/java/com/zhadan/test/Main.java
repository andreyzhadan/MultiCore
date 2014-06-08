package com.zhadan.test;

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                StaticTest.f();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                StaticTest.g();
            }
        }).start();
    }
}

package com.zhadan.test;

/**
 * Created by andrewzhadan on 5/1/14.
 */
public class A {
    private Object obj;

    public A(Object obj) {
        this.obj = obj;
    }

    public void doJob() throws InterruptedException {
        synchronized (obj) {
            System.out.println("startA");
            Thread.sleep(5000);
            System.out.println("endA");
        }
    }
}

package com.zhadan.test;

/**
 * Created by andrewzhadan on 5/1/14.
 */
public class B {
    private Object obj;

    public B(Object obj) {
        this.obj = obj;
    }

    public void doJob() throws InterruptedException {
        synchronized (obj) {
            System.out.println("startB");
            Thread.sleep(5000);
            System.out.println("endB");
        }
    }
}
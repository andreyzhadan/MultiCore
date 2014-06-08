package com.zhadan.lesson5;

import java.util.concurrent.CountDownLatch;

/**
 * Created by andrewzhadan on 6/7/14.
 */
public class App53_wait_set {
    public static final int LENGTH = 4;

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        Thread[] threadArray = new Thread[LENGTH];
        final CountDownLatch latch = new CountDownLatch(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            threadArray[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (lock) {
                        latch.countDown();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            threadArray[i].start();
        }

        latch.await();

        synchronized (lock) {
            for (int i = 0; i < LENGTH; i++) {
                System.out.println(threadArray[i].getState());
            }
        }
    }
}

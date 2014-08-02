package com.zhadan.golovach.lesson5;

/**
 * Created by andrewzhadan on 6/7/14.
 */
public class App52_wait_set {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
//        final CountDownLatch latch = new CountDownLatch(1);
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (lock) {
//                    latch.countDown();
                    try {
                        lock.wait();

//                        for (int i = 0; i < 100000; i++) {
//                            int j=i+3;
//                        }
                    } catch (InterruptedException e) {
                        throw new Error("NEVER");
                    }
                }
            }
        });
        newThread.start();

//        latch.await();

        synchronized (lock) {
//            lock.notify();
            for (int i = 0; i < 1000000; i++) {
                if (i % 100 == 0) {
                    System.out.println(newThread.getState());
                }
            }
        }
//        Thread.sleep(1);
        System.out.println(newThread.getState());
    }
}

package com.zhadan.multicore;

/**
 * Created by andrewzhadan on 4/26/14.
 */
public class AppJMM {
    static volatile boolean ready = false;
    static int data = 0;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(1000);
                data = 1;
                ready = true;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!ready);
                System.out.println(data);
            }
        }).start();
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

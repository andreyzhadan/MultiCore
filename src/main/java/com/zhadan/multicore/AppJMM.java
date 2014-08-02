package com.zhadan.multicore;

import static java.lang.System.out;

/**
 * Created by andrewzhadan on 4/26/14.
 */
public class AppJMM {
    static volatile boolean ready = false;
    static int data = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            sleep(1000);
            data = 1;
            ready = true;
        }).start();

        new Thread(() -> {
            while (!ready) ;
            out.println(data);
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

package com.zhadan.test;

/**
 * Created by andrewzhadan on 5/3/14.
 */
public class StaticTest {
    public static synchronized void f() {
        System.out.println("f started");
        sleep(1000);
        System.out.println("f finished");
    }

    public static synchronized void g() {
        System.out.println("g started");
        sleep(1000);
        System.out.println("g finished");
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

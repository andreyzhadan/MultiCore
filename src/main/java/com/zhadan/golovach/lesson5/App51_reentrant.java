package com.zhadan.golovach.lesson5;

/**
 * Created by andrewzhadan on 6/4/14.
 */
public class App51_reentrant {
    public static void main(String[] args) {
        f();
        // StampedLock
    }

    private static void f() {
        g();
    }

    private static void g() {
        Class clazz = App51_reentrant.class;
        synchronized (clazz) {
            h();
        }
    }

    private static void h() {
        System.out.println("QQQ");
    }
}

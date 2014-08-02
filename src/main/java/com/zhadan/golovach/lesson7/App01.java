package com.zhadan.golovach.lesson7;

interface X {
    public void g();
}

/**
 * Created by andrewzhadan on 6/19/14.
 */
public class App01 {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("Hello1")).start();

        new Thread(App01::run2).start();

        // X x = () -> System.out.println("Hello3");

        Runnable f0 = App01::run2; //link to method
        Runnable f1 = () -> System.out.println("Hello3");

    }

    public static void run2() { //no args, no exceptions
        System.out.println("Hello2");
    }
}
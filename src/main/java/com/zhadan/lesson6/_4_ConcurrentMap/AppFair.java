package com.zhadan.lesson6._4_ConcurrentMap;

// A B A B A A A B B A B B A A B B A
// A A A A A A A A A A A A A A A A A
public class AppFair {
    public static void main(String[] args) {
        final Object ref = new Object();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (ref) {
                        System.out.println("A");
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (ref) {
                        System.out.println("B");
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (ref) {
                        System.out.println("C");
                    }
                }
            }
        }).start();
    }
}

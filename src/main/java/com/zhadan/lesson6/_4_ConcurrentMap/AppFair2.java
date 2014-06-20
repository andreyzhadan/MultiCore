package com.zhadan.lesson6._4_ConcurrentMap;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// A B A B A A A B B A B B A A B B A
// A A A A A A A A A A A A A A A A A
public class AppFair2 {
    public static void main(String[] args) {
        final Lock ref = new ReentrantLock(true);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    ref.lock();
                    System.out.println("A");
                    ref.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    ref.lock();
                    System.out.println("B");
                    ref.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    ref.lock();
                    System.out.println("C");
                    ref.unlock();
                }
            }
        }).start();
    }
}

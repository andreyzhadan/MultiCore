package com.zhadan.lesson5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by andrewzhadan on 6/7/14.
 */
public class App52_wait_set_condition {
    public static void main(String[] args) throws InterruptedException {
        Lock mutex = new ReentrantLock(true);
        Condition condition1 = mutex.newCondition();
        Condition condition2 = mutex.newCondition();
        mutex.lock();
        try {
            condition1.signal();
            condition2.signal();
//            condition1.await();
        } finally {
            mutex.unlock();
        }
    }
}

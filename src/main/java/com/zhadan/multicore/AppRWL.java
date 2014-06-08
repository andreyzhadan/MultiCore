package com.zhadan.multicore;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by andrewzhadan on 5/1/14.
 */
public class AppRWL {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock(true);
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();

//        new Thread(new Locker(readLock, "read")).start();
//        new Thread(new Locker(readLock, "read")).start();
//
//        new Thread(new Locker(writeLock, "write")).start();
//        new Thread(new Locker(writeLock, "write")).start();
    }
}

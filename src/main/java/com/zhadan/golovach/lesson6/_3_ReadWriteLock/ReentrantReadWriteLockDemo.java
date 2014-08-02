package com.zhadan.golovach.lesson6._3_ReadWriteLock;

import java.util.concurrent.locks.*;

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        // read/write lock
        // shared/exclusive lock
        ReadWriteLock lock = new ReentrantReadWriteLock(true);
        Lock rLock = lock.readLock();
        Lock rLock2 = lock.readLock();
        Lock wLock = lock.writeLock();
        Lock wLock2 = lock.writeLock();
        System.out.println(rLock == rLock2);
        System.out.println(wLock == wLock2);

        /**
         *
         *
         *
         *
         */
    }
}

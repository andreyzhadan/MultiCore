package com.zhadan.guidelines;

import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by andrewzhadan on 6/8/14.
 */
public final class Flag {
    private boolean flag = true;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public synchronized void toggle() {
        writeLock.lock();
        try {
            flag ^= true;//Same as flag=!flag
        } finally {
            writeLock.unlock();
        }
    }

    public boolean getFlag() {
        readLock.lock();
        try {
            return flag;
        } finally {
            readLock.unlock();
        }
    }
}

class MainFlag {
    public static void main(String[] args) {
        Flag flag = new Flag();
        System.out.println(flag.getFlag());
    }
}

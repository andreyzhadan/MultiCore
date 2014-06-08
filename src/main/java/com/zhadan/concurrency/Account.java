package com.zhadan.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by azhadan on 7/17/13.
 */
public class Account {
    private int balance;
    private String name;
    private Lock locker;
    private AtomicInteger failCounter;

    public Account(int initBalance, String name) {
        this.balance = initBalance;
        this.name = name;
        this.locker = new ReentrantLock();
        this.failCounter = new AtomicInteger(0);
    }

    public AtomicInteger getFailCounter() {
        return failCounter;
    }

    public void incFailedTransferCount() {
        failCounter.incrementAndGet();
    }

    public Lock getLocker() {
        return locker;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public String getName() {
        return name;
    }
}

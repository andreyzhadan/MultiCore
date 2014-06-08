package com.zhadan.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by azhadan on 7/17/13.
 */
public class Operations {

    private static final Logger LOGGER = Logger.getLogger(Operations.class.getName());
    private static final long WAIT_MILISEC = 1000;
    private static final long SLEEP_TIME = 500;

    public static void main(String[] args) {
        final Account a = new Account(1000, "a");
        final Account b = new Account(2000, "b");

        new Thread(new Runnable() {
            public void run() {
                transfer(a, b, 500);
            }
        }).start();

        transfer(b, a, 300);
    }

    private static void transfer(Account acc1, Account acc2, int amount) {
        if (acc1.getBalance() < amount)
            return;
        LOGGER.info("Try to lock " + acc1.getName() + " thread " + Thread.currentThread().getName());
        //avoid deadlocks
        try { //synchronized (acc1) {
            if (acc1.getLocker().tryLock(WAIT_MILISEC, TimeUnit.MILLISECONDS)) {
                LOGGER.info("Locked at " + acc1.getName() + " thread " + Thread.currentThread().getName());
                LOGGER.info("Inline try to lock " + acc2.getName() + " thread " + Thread.currentThread().getName());
                try { //synchronized (acc2) {
                    if (acc2.getLocker().tryLock(WAIT_MILISEC, TimeUnit.MILLISECONDS)) {
                        try {
                            LOGGER.info("Inline locked at " + acc2.getName() + " thread " + Thread.currentThread().getName());
                            Thread.sleep(SLEEP_TIME);
                            acc1.withdraw(amount);
                            acc2.deposit(amount);
                        } finally {
                            acc2.getLocker().unlock();
                        }
                    } else {
                        acc1.incFailedTransferCount();
                        acc2.incFailedTransferCount();
                        LOGGER.warning("Sorry, acc2 locked");
                    }
                } finally {
                    acc1.getLocker().unlock();
                }
            } else {
                acc1.incFailedTransferCount();
                acc2.incFailedTransferCount();
                LOGGER.warning("Sorry, acc1 locked");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("Transfer is successful");
    }
}

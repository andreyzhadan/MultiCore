package com.zhadan.concurrency;

import javax.naming.InsufficientResourcesException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by azhadan on 7/18/13.
 */
public class Transfer implements Callable<Boolean> {

    private static final Logger LOGGER = Logger.getLogger(Operations.class.getName());
    private static final long WAIT_MILISEC = 3000;
    private final Account acc1;
    private final Account acc2;
    private final int amount;
    private final CountDownLatch countDownLatch;

    public Transfer(CountDownLatch countDownLatch, Account acc1, Account acc2, int amount) {
        this.countDownLatch = countDownLatch;
        this.acc1 = acc1;
        this.acc2 = acc2;
        this.amount = amount;
    }

    public Boolean call() throws Exception {
        //avoid deadlocks
        //synchronized (acc1) {
        System.out.println("Waiting to start....");
        //countDownLatch.await();
        LOGGER.info("Try to lock " + acc1.getName() + " thread " + Thread.currentThread().getName());
        if (acc1.getLocker().tryLock(WAIT_MILISEC, TimeUnit.MILLISECONDS)) {
            LOGGER.info("Locked at " + acc1.getName() + " thread " + Thread.currentThread().getName());
            LOGGER.info("Inline try to lock " + acc2.getName() + " thread " + Thread.currentThread().getName());
            if (acc1.getBalance() < amount)
                throw new InsufficientResourcesException();
            try { //synchronized (acc2) {
                if (acc2.getLocker().tryLock(WAIT_MILISEC, TimeUnit.MILLISECONDS)) {
                    try {
                        LOGGER.info("Inline locked at " + acc2.getName() + " thread " + Thread.currentThread().getName());
                        int random = new Random().nextInt(1000);
                        LOGGER.info("Sleeping for " + random + " MILLIS");
                        Thread.sleep(random);
                        acc1.withdraw(amount);
                        acc2.deposit(amount);
                    } finally {
                        acc2.getLocker().unlock();
                    }
                } else {
                    acc1.incFailedTransferCount();
                    acc2.incFailedTransferCount();
                    LOGGER.warning("Sorry, acc2 locked");
                    return false;
                }
            } finally {
                acc1.getLocker().unlock();
            }
        } else {
            acc1.incFailedTransferCount();
            acc2.incFailedTransferCount();
            LOGGER.warning("Sorry, acc1 locked");
            return false;
        }
        LOGGER.info("Transfer is successful");
        return true;
    }
}

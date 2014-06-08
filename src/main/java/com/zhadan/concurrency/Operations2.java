package com.zhadan.concurrency;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by azhadan on 7/18/13.
 */
public class Operations2 {
    public static void main(String[] args) {
        final Account acc1 = new Account(2000, "acc1");
        final Account acc2 = new Account(1000, "acc2");

        ExecutorService service = Executors.newFixedThreadPool(3);
        Random rand = new Random();

        CountDownLatch startLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            Future<Boolean> future;
            int randomValue = rand.nextInt(1000);
            if (randomValue % 2 == 0) {
                future = service.submit(new Transfer(startLatch, acc1, acc2, randomValue));
                System.out.println("Try to send " + randomValue + " from " + acc1.getName() + " balance " + acc1.getBalance() + " to " + acc2.getName() + " balance " + acc2.getBalance());
            } else {
                future = service.submit(new Transfer(startLatch, acc2, acc1, randomValue));
                System.out.println("Try to send " + randomValue + " from " + acc2.getName() + " balance " + acc2.getBalance() + " to " + acc1.getName() + " balance " + acc1.getBalance());
            }
            try {
                System.out.println("Transaction is " + (future.get() ? "ok" : "not ok"));
            } catch (ExecutionException e) {
                System.out.println("You don't have enough money on your account " + acc1.getBalance());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //startLatch.countDown();
        service.shutdown();
    }
}

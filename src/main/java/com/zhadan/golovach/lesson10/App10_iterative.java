package com.zhadan.golovach.lesson10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by andrewzhadan on 8/16/14.
 */
public class App10_iterative {
    public static void main(String[] args) throws InterruptedException {
        AtomicLong result = new AtomicLong(0);
        List<Callable<Void>> tasksList = new ArrayList<>(100); //100 callables
        for (int i = 0; i < 100; i++) {
            final int finalI = i;
            tasksList.add(() -> {
                for (int index = 10_000 * finalI; index < 10_000 * (finalI + 1); index++) {
                    if (index % 3 != 0 && index % 5 != 0) {
                        result.addAndGet(index);
                    }
                }
                return null;
            });
        }
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.invokeAll(tasksList);
        System.out.println(result);
        pool.shutdown();
    }
}

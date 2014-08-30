package com.zhadan.golovach.lesson10;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static java.lang.Runtime.getRuntime;

/**
 * Created by andrewzhadan on 8/16/14.
 */
public class App10_recursive_task {
    private static final int THRESHOLD = 1_000;

    public static void main(String[] args) {
        long st = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(getRuntime().availableProcessors());
        long result = forkJoinPool.invoke(new Task(0, 10_000_000));
        long end = System.currentTimeMillis();
        System.out.println(result + " / " + (end - st));
    }

    public static boolean isPrime(int n) {
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static class Task extends RecursiveTask<Long> {
        private final int from;
        private final int to;

        public Task(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            if (to - from < THRESHOLD) {
                long result = 0;
                for (int index = from; index < to; index++) {
                    if (isPrime(index)) {
                        result += index;
                    }
                }
                return result;
            } else {
                int mid = (from + to) >>> 1;
                Task taskLeft = new Task(from, mid);
                Task taskRight = new Task(mid, to);
//                taskLeft.fork();
                taskRight.fork();
                invokeAll(taskLeft, taskRight);
                return taskLeft.join() + taskRight.join();
            }
        }
    }
}

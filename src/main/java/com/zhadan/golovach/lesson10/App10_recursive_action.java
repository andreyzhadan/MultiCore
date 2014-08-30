package com.zhadan.golovach.lesson10;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Runtime.*;

/**
 * Created by andrewzhadan on 8/16/14.
 */
public class App10_recursive_action {
    private static final int THRESHOLD = 10_000;

    public static void main(String[] args) {
        AtomicLong result = new AtomicLong(0);
        // большое количество маленьких задач  > 10-100 раз чем ядер
        ForkJoinPool forkJoinPool = new ForkJoinPool(getRuntime().availableProcessors());
        forkJoinPool.invoke(new Task(0, 1_000_000, result));
        System.out.println(result.get());
    }

    public static class Task extends RecursiveAction {
        private final int from;
        private final int to;
        private final AtomicLong result;

        public Task(int from, int to, AtomicLong result) {
            this.from = from;
            this.to = to;
            this.result = result;
        }

        @Override
        protected void compute() {
            if (to - from < THRESHOLD) {
                for (int index = from; index < to; index++) {
                    if (index % 5 != 0 && index % 7 != 0) {
                        result.addAndGet(index);
                    }
                }
            } else {
                int mid = (from + to) >>> 1;
                Task taskLeft = new Task(from, mid, result);
                Task taskRight = new Task(mid, to, result);
                invokeAll(taskLeft, taskRight);
            }
        }
    }
}

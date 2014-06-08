package com.zhadan.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentCollections {
    private static final int SIZE = 10000;

    //Compare time for concurrent get items from different implementations of list
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list1 = Collections.synchronizedList(new ArrayList<Integer>());
        List<Integer> list2 = new CopyOnWriteArrayList<Integer>();

        fillList(list1, SIZE);
        fillList(list2, SIZE);

        System.out.println("Synchronized list: ");
        checkList(list1);

        System.out.println("CopyOnWrite list: ");
        checkList(list2);
    }

    private static void checkList(List<Integer> list) throws ExecutionException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService ex = Executors.newFixedThreadPool(3);
        Future<Long> f1 = ex.submit(new ListRunner(0, SIZE / 3, list, latch));
        Future<Long> f2 = ex.submit(new ListRunner(SIZE / 3, 2 * SIZE / 3, list, latch));
        Future<Long> f3 = ex.submit(new ListRunner(2 * SIZE / 3, SIZE, list, latch));

        latch.countDown();
        System.out.println("Thread 1:" + f1.get() / 1000);
        System.out.println("Thread 2:" + f2.get() / 1000);
        System.out.println("Thread 3:" + f3.get() / 1000);
    }

    private static void fillList(List<Integer> list, int count) {
        for (int i = 0; i < count; i++) {
            list.add(i, i);
        }
    }

    private static class ListRunner implements Callable<Long> {
        private CountDownLatch latch;
        private int start;
        private int end;
        private List<Integer> list;

        public ListRunner(int start, int end, List<Integer> list, CountDownLatch latch) {
            this.start = start;
            this.end = end;
            this.list = list;
            this.latch = latch;
        }

        @Override
        public Long call() throws Exception {
            latch.await();
            long startTime = System.nanoTime();
            for (int i = start; i < end; i++) {
                list.get(i);
            }
            return System.nanoTime() - startTime;
        }
    }
}

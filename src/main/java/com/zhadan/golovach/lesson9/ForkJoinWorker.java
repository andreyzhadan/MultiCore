package com.zhadan.golovach.lesson9;


import jsr166y.ForkJoinPool;

import static java.util.stream.LongStream.range;

/**
 * Created by andrewzhadan on 7/19/14.
 */
public class ForkJoinWorker {

    public static void main(String[] args) {

        // Check the number of available processors
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("No of processors: " + processors);

        int n = 50;
//        long start = System.currentTimeMillis();

        FibonacciProblem bigProblem = new FibonacciProblem(n);

//        FibonacciTask task = new FibonacciTask(bigProblem);
        ForkJoinPool pool = new ForkJoinPool(processors);
//        pool.invoke(task);
//
//        long result = task.result;
//        System.out.println("Computed Result: " + result);
//        long end = System.currentTimeMillis();
//
//        System.out.println("Elapsed Time: " + (end - start));
        range(1,1_000_000).parallel();


    }

}

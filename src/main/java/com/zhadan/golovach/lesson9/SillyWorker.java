package com.zhadan.golovach.lesson9;

/**
 * Created by andrewzhadan on 7/19/14.
 */

public class SillyWorker {

    public static void main(String[] args) throws Exception {

        int n = 50;

        FibonacciProblem bigProblem = new FibonacciProblem(n);

        long start = System.currentTimeMillis();
        long result = bigProblem.solve();
        long end = System.currentTimeMillis();

        System.out.println("Computing Fib number: " + n);
        System.out.println("Computed Result: " + result);
        System.out.println("Elapsed Time: " + (end - start));

    }

}

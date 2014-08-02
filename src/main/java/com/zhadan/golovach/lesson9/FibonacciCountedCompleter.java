package com.zhadan.golovach.lesson9;

import java.util.concurrent.CountedCompleter;

/**
 * Created by andrewzhadan on 7/23/14.
 */
public class FibonacciCountedCompleter extends CountedCompleter<Void> {
    @Override
    public void compute() {

    }

//    private static final int THRESHOLD = 20;
//
//    public FibonacciCountedCompleter(CountedCompleter<?> parent) {
//        super(parent);
//    }
//
//    @Override
//    public void compute() {
//        if (problem.n < THRESHOLD) { // easy problem, don't bother with parallelism
//            problem.solve();
//        } else {
//            setPendingCount(2);// expect two subtasks
//            FibonacciTask worker1 = new FibonacciTask(new FibonacciProblem(problem.n - 1));
//            FibonacciTask worker2 = new FibonacciTask(new FibonacciProblem(problem.n - 2));
//            worker1.fork();
//            worker2.fork();
//        }
//        tryComplete();// increment and try to call completion
//    }
}

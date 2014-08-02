package com.zhadan.golovach.lesson9;

import java.util.concurrent.RecursiveTask;

/**
 * Created by andrewzhadan on 7/19/14.
 */
public class FibonacciTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 20;
    public long result;
    private FibonacciProblem problem;

    public FibonacciTask(FibonacciProblem problem) {
        this.problem = problem;
    }

    @Override
    public Long compute() {
        if (problem.n < THRESHOLD) { // easy problem, don't bother with parallelism
            result = problem.solve();
        } else {
            FibonacciTask worker1 = new FibonacciTask(new FibonacciProblem(problem.n - 1));
            FibonacciTask worker2 = new FibonacciTask(new FibonacciProblem(problem.n - 2));
            worker1.fork();
            worker2.fork();// - => compute

//            ForkJoinTask.invokeAll(worker1,worker2);

            result = worker2.join() + worker1.join();

        }
        return result;
    }

}
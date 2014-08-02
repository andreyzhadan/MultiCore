package com.zhadan.golovach.lesson9;

import java.util.concurrent.RecursiveTask;

/**
 * Created by andrewzhadan on 7/13/14.
 */
public class StandardTask extends RecursiveTask<Long> {
    @Override
    protected Long compute() {
        return null;
    }

//
//    private final Problem problem;
//    private int l;
//    private int r;
//
//    public StandardTask(Problem problem, int l, int r) {
//        this.problem = problem;
//        this.l = l;
//        this.r = r;
//    }
//
//    @Override
//    protected Long compute() {
//        if (r - l <= THRESHOLD) {
//            return problem.solve(l, r);
//        }
//
//        int mid = (l + r) >>> 1;
//        ForkJoinTask t1 = new StandardTask(problem, l, mid);
//        ForkJoinTask t2 = new StandardTask(problem, mid, r);
//
////        t1.fork();
////        t2.fork();
//        ForkJoinTask.invokeAll(t1, t2);
//
//        long res = 0;
//        res += t1.join();
//        res += t2.join();
//
//        return res;
//    }
}

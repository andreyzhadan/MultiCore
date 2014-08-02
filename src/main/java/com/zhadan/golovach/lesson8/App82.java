package com.zhadan.golovach.lesson8;

import java.util.concurrent.CompletableFuture;

import static java.lang.Math.PI;
import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Created by andrewzhadan on 6/27/14.
 */
public class App82 {
    public static void main(String[] args) throws InterruptedException {
        int[] k = new int[1];
        CompletableFuture<Integer> comp1 = supplyAsync(() -> {
            for (int i = 0; i < Integer.MAX_VALUE / 2; i++) {
                k[0] = i;
            }
            return "42";
        }).thenApply(Integer::valueOf);

        comp1.thenApply(x -> x * x * PI)
                .thenAccept(System.out::println);

        System.out.println("end");
    }
}

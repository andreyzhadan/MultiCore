package com.zhadan.golovach.lesson8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

/**
 * Created by andrewzhadan on 6/27/14.
 */
public class App82 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] k = new int[1];
        CompletableFuture<Integer> comp1 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < Integer.MAX_VALUE / 2; i++) {
                k[0] = i;
            }
            return "42";
        }).thenApply(Integer::valueOf);

        System.out.println("end");

//        comp1.thenCombineAsync()
        System.out.println(comp1.get());

        BiFunction<String, String, String> combiner = (s1, s2) -> s1 + s2;
        System.out.println(combiner.apply("Hello, ", "World"));
    }
}

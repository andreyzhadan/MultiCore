package com.zhadan.golovach.lesson8;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.concurrent.Executors.newCachedThreadPool;

/**
 * Created by andrewzhadan on 6/26/14.
 */
public class App81 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = newCachedThreadPool();

        //no files
        Future<byte[]> futBytes = pool.submit(() -> readAllBytes(get("tmp0.txt")));
        System.out.println(futBytes.isDone());

        byte[] bytes = futBytes.get();
        System.out.println(futBytes.isDone());
        System.out.println(Arrays.toString(bytes));
    }
}

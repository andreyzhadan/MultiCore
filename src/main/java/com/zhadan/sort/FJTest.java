package com.zhadan.sort;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by andrewzhadan on 8/24/14.
 */
public class FJTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalNumbers = scanner.nextInt();
        int[] numbers = new int[totalNumbers];
        for (int i = 0; i < totalNumbers; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println("Unsorted array: "
                + Arrays.toString(numbers));
        DivideTask task = new DivideTask(numbers);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(task);
        System.out.println("Sorted array: "
                + Arrays.toString(task.join()));

        System.out.println(1 << 13);

    }
}

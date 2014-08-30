package com.zhadan.golovach.lesson10.sort;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by andrewzhadan on 8/24/14.
 */
class DivideTask extends RecursiveTask<int[]> {
    int[] arrayToDivide;

    public DivideTask(int[] arrayToDivide) {
        this.arrayToDivide = arrayToDivide;
    }

    @Override
    protected int[] compute() {
        if (arrayToDivide.length > 1) {
            List<int[]> partitionedArray = partitionArray();

            DivideTask task1 = new DivideTask(partitionedArray.get(0));
            DivideTask task2 = new DivideTask(partitionedArray.get(1));
            task1.fork();
            task2.fork();

            //Wait for results from both the tasks
            int[] array1 = task1.join();
            int[] array2 = task2.join();

            //Initialize a merged array
            int[] mergedArray = new int[array1.length + array2.length];
            mergeArrays(array1, array2, mergedArray);
            return mergedArray;
        }
        return arrayToDivide;
    }

    private List<int[]> partitionArray() {
        int[] partition1 = Arrays.copyOfRange(arrayToDivide, 0,
                arrayToDivide.length / 2);
        int[] partition2 = Arrays.copyOfRange(arrayToDivide,
                arrayToDivide.length / 2,
                arrayToDivide.length);
        return Arrays.asList(partition1, partition2);

    }

    private void mergeArrays(
            int[] array1,
            int[] array2,
            int[] mergedArray) {
        int i = 0, j = 0, k = 0;
        while ((i < array1.length) && (j < array2.length)) {
            if (array1[i] < array2[j]) {
                mergedArray[k] = array1[i++];
            } else {
                mergedArray[k] = array2[j++];
            }
            k++;
        }

        if (i == array1.length) {
            for (int a = j; a < array2.length; a++) {
                mergedArray[k++] = array2[a];
            }
        } else {
            for (int a = i; a < array1.length; a++) {
                mergedArray[k++] = array1[a];
            }
        }
    }
}

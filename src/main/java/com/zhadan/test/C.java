package com.zhadan.test;

/**
 * Created by andrewzhadan on 7/3/14.
 */
public class C {
    public static void main(String[] args) {
        int[] A=new int[5];

    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += A[j];
            }
            for (int j = i; j < A.length; j++) {
                sum -= A[j];
            }
            if (sum < minSum) {
                minSum = sum;
            }
        }
        return minSum;
    }
}

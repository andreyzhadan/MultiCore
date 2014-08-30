package com.zhadan.golovach.lesson10;

import java.util.stream.LongStream;

/**
 * Created by andrewzhadan on 8/16/14.
 */
public class App10_stream {
    public static void main(String[] args) {
        //сколько потоков использовать, на сколько кусков резать не указывается)
        //"hello"+"world" ->асоциативно но !комутативно
        long sum = LongStream.range(0, 1_000_000).parallel()
                .filter(App10_stream::isPrime)
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
    }

    private static boolean isPrime(long value) {
        if (value % 2 == 0) return false;
        for (int i = 3; i * i <= value; i += 2) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }
}

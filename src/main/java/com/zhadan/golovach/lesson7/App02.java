package com.zhadan.golovach.lesson7;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.Comparator.nullsFirst;
import static java.util.Comparator.reverseOrder;

interface I {
    static void f() {
    }
}

/**
 * Created by andrewzhadan on 6/19/14.
 */
public class App02 {
    public static void main(String[] args) {
        Executor pool = Executors.newCachedThreadPool();

        List<String> list = Arrays.asList("A", "C", null, "B");
        Collections.sort(list, nullsFirst(reverseOrder()));
        System.out.println(list);
    }
}

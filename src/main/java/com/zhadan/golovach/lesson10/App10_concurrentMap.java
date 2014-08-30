package com.zhadan.golovach.lesson10;

import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Runtime.getRuntime;

/**
 * Created by andrewzhadan on 8/16/14.
 */
public class App10_concurrentMap {
    public static void main(String[] args) {
        ConcurrentHashMap<Long, Long> map = new ConcurrentHashMap<>();

        int cpu = getRuntime().availableProcessors();
        Long result1 = map.search(cpu, (key, value) -> key.equals(value) ? key : null);

        Long result2 = map.reduceKeys(cpu, (key0, key1) -> key0 + key1);
    }
}

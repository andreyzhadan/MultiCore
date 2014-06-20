package com.zhadan.lesson6._3_ReadWriteLock;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.synchronizedMap;

public class App00 {
    public static void main(String[] args) {
        // Java 1 style
        Map<String, String> map0 = new Hashtable<>();
        // java.util.concurrent style (Java 5)
        Map<String, String> map2 = new ConcurrentHashMap<>(16, 0.75f, 4);
        // Collection API style (Java 2)
        Map<String, String> map1 = synchronizedMap(new HashMap<String, String>());
        // exercise in j.u.c ReadWriteLock
        Map<String, String> map3 = new RWMapDecorator<>(new HashMap<String, String>());
    }
}

package com.zhadan.lesson6._4_ConcurrentMap;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class App02_ConcurrentSkipListMap {
    public static void main(String[] args) {
        ConcurrentMap map = new ConcurrentSkipListMap();
    }
}

// Map:
// HashMap -> ConcurrentHashMap

// NavigableMap / SortedMap
// TreeMap -> *
//    *    -> ConcurrentSkipListMap
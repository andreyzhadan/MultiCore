package com.zhadan.lesson6._4_ConcurrentMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class App00_ConcurrentHashMap_up_to_java7 {
    public static void main(String[] args) {
        ConcurrentMap<String, Long> map
                = new ConcurrentHashMap<>(16, 0.75f, 64);

//        while (true) {
//            // map.lock()
//            Long value = map.get("red");
//            if (value == null) {
//                map.put("red", (long) 1);
//            } else {
//                map.put("red", value + 1);
//            }
//            // map.unlock()
//        }

        while (true) {
            while (true) {
                Long oldValue = map.putIfAbsent("red", 1L);
                if (oldValue != null) {
                    if (map.replace("red", oldValue, oldValue + 1)) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }
}

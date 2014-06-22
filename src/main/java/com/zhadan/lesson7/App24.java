package com.zhadan.lesson7;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
interface ThreeFunction<T0, T1, T2, R> {
    R apply(T0 to, T1 t1, T2 t2);
}

@FunctionalInterface
interface FourFunction<T0, T1, T2, T3, R> {
    R apply(T0 to, T1 t1, T2 t2, T3 t3);
}

class XYZ {
    public Integer f(String s, Object o, int[] i) {
        return null;
    }
}

/**
 * Created by andrewzhadan on 6/22/14.
 */
public class App24 {
    public static void main(String[] args) {

        Function<String, Integer> f0 = Integer::valueOf;
        Function<String, Integer> f1 = s -> Integer.valueOf(s);
        Function<String, Integer> f2 = s -> {
            return Integer.valueOf(s);
        };
        Function<String, Integer> f3 = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        };
        System.out.println(f3.apply("123") + 300);


        Supplier<ArrayList> ref = ArrayList::new;
        System.out.println(ref.get());


        Point p = new Point(10, 20);
        Predicate<Object> pred = p::equals;
        //p=null //magic!!
        System.out.println(pred.test(new Point(20, 10)));
        System.out.println(pred.test(new Point(10, 20)));


        BiFunction<Point, Object, Boolean> ref2 = Point::equals;


        ThreeFunction<String, Object, int[], Integer> ref3 = new XYZ()::f;
        System.out.println(ref3.apply("s", "s", new int[3]));


        FourFunction<XYZ, String, Object, int[], Integer> ref4 = XYZ::f;
    }
}

package com.zhadan.golovach.lesson8;

import java.util.Optional;


/**
 * Created by andrewzhadan on 6/26/14.
 */
public class App80 {
    public static void main(String[] args) {
//        Optional<Integer> opNotNull = Optional.of(23);
//        Optional<Integer> opNull = Optional.ofNullable(null);
//        Optional sum;
//        if (opNull.isPresent() && opNotNull.isPresent()) {
//            sum = Optional.ofNullable(opNull.get() + opNotNull.get());
//        } else {
//            sum = Optional.ofNullable(null);
//        }
//        System.out.println(sum);

//        Optional<Integer> s1= Optional.of(23);
//        Optional<Integer> s2 = Optional.ofNullable(null);
//        Optional<Integer> summ = Stream.of(23,null).filter(e-> e!=null).reduce((x, y) -> x + y);
//        if (summ.isPresent()) {
//            System.out.println(summ);
//        } else {
//            System.out.println(2);
//        }

        String s = "23";//"23";//null
        Optional<String> op = Optional.ofNullable(s);
        op.ifPresent(System.out::println);
        Optional<Integer> op2 = op.map(Integer::valueOf);
        Optional<Integer> op3 = op2.map(x -> x * x);
        if (op3.isPresent()) {
            System.out.println(op3.get());
        } else {
            System.out.println("This is empty " + op3);
        }
    }
}

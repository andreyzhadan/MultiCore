package com.zhadan.golovach.lesson7;

import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * Created by andrewzhadan on 6/22/14.
 */
public class App25 {
    public static void main(String[] args) {
        //Iterator
        Stream<Integer> stream = asList(
                new Person("Mike Tyson", 45),
                new Person("Vera Vera", 18),
                new Person("Anna Belaia", 25))
                .parallelStream()
                .filter(p -> p.getAge() > 21)
                .map(Person::getName)
                .map(name -> {
                    System.out.println(name);
                    return name;
                })
                .map(String::length);

        System.out.println(stream.reduce(1, (a, b) -> a * b));

//        stream.forEach(System.out::println);

//        stream.forEach(x -> System.out.println(Thread.currentThread()));
    }
}

class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return getName() + "/" + getAge();
    }
}

package com.zhadan;

import groovyx.gpars.extra166y.ParallelArray;
import jsr166y.ForkJoinPool;

import java.util.*;
import java.util.stream.Stream;

import static java.lang.Character.getNumericValue;
import static java.lang.System.arraycopy;
import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.asList;
import static java.util.Spliterator.ORDERED;
import static java.util.stream.Stream.of;

/**
 * Created by azhadan on 8/8/14.
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> coins = new ArrayList<>(Arrays.asList(1, 3, 5));
        Stream<String> stream = of("0", "1");
        for (int i = 1; i < coins.size(); i++) {
            stream = stream.flatMap(x -> of(x + "0", x + "1"));
        }
        Set<Integer> sums = new TreeSet<>();

        stream.forEach(seq -> {
            int sum = 0;
            for (int i = 0; i < seq.length(); i++) {
                sum = sum + (getNumericValue(seq.charAt(i))) * coins.get(i);
            }
            sums.add(sum);
            System.out.println("Seq " + seq + " sum " + sum);
        });
        System.out.println(sums);
        int elem = 0;
        while (true) {
            if (!sums.contains(elem)) {
                System.out.println("Not found " + elem);
                break;
            }
            elem++;
        }

//        Iterator<String> iterator = stream.iterator();
//        while (iterator.hasNext()) {
//              System.out.println(iterator.next());
//        }

//        stream.spliterator().forEachRemaining(System.out::println);

//        Spliterator<String> spliterator = stream.spliterator();
//        do {
//
//        } while (spliterator.tryAdvance(System.out::println));
//        spliterator.tryAdvance(System.out::println);

//        System.out.println(stream.findAny().get());

//        Set<Integer> sums = new TreeSet<>();
//        sums.addAll(coins);
//        for (int i = 0; i < coins.size(); i++) {
//
//        }

//        long end = System.currentTimeMillis();
//        System.out.println("Intermediate ops " + (middle - start) + " Terminal ops " + (end - middle));


//        spliteratorCharacteristics();
    }

    private static void spliteratorCharacteristics() {
        List<Integer> integers = asList(1, 3, 2);

        List<Integer> list = new ArrayList<>(integers);
        int characteristics = list.spliterator().characteristics();
        System.out.println("ArrayList Ordered " + ((characteristics & ORDERED) != 0));


        Set<Integer> set = new HashSet<>(integers);
        characteristics = set.spliterator().characteristics();
        System.out.println("HashSet Ordered " + ((characteristics & ORDERED) != 0));

        list.stream()
                .parallel()
                .map(x -> x * x)
                .forEach(System.out::println);

//                .iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
    }

    private static void parallelTester() {
        int arrayLength = 100_000_000;
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        double seqTime = 0, parTime = 0, arrParTime = 0;
        Integer[] data = new Integer[arrayLength];
        Integer[] data2 = new Integer[arrayLength];
        fillArray(arrayLength, data);

        ParallelArray parallelArray = ParallelArray.createFromCopy(data, forkJoinPool);
        arraycopy(data, 0, data2, 0, arrayLength);

//        double seqStart = currentTimeMillis();
//        Arrays.sort(data);
//        seqTime += (currentTimeMillis() - seqStart);

        double parStart = currentTimeMillis();
        parallelArray.sort();
        parTime += (currentTimeMillis() - parStart);

        double arrParStart = currentTimeMillis();
        Arrays.parallelSort(data2);
        arrParTime += (currentTimeMillis() - arrParStart);

        System.out.println("Arrays sequential " + seqTime);
        System.out.println("ParallelArray " + parTime);
        System.out.println("Arrays parallel " + arrParTime);
    }

    private static void parallelUsage() {
        //ParallelArray
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        Integer[] list = new Integer[]{1, 4, 2, 5, 3};
        ParallelArray<Integer> parallelArray = ParallelArray.createFromCopy(list, forkJoinPool);
        ParallelArray<Integer> parallelArrayProcessed = parallelArray.withFilter(x -> x >= 2).all();
        System.out.println(parallelArrayProcessed.summary());

        //Arrays
        List<Integer> list1 = asList(11, 68, 34, 1, 100);
        Integer min = list1.parallelStream().filter(x -> x >= 2).min(Comparator.<Integer>naturalOrder()).get();
        System.out.println(min);

        //[a,a+b,a+b+c,a+b+c+d]
        Arrays.parallelPrefix(list, (x, y) -> x + y);
        System.out.println(Arrays.toString(list));

        Arrays.parallelSetAll(list, (index) -> 10 + index);
        System.out.println(Arrays.toString(list));

//        LongStream.generate(() -> (long) (1000 * Math.random()));
    }

    public static void fillArray(int arrayLength, Integer[] intArray) {
        Random random = new Random(10000);
        for (int i = 0; i < arrayLength; i++) {
            intArray[i] = random.nextInt(100);
        }

    }
}

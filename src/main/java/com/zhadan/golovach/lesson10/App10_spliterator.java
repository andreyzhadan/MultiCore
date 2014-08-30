package com.zhadan.golovach.lesson10;

import java.util.function.LongConsumer;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.OfLong;

/**
 * Created by andrewzhadan on 8/16/14.
 */
public class App10_spliterator {
    public static void main(String[] args) {
        LongRange longRange = new LongRange(0, 1_000_000);
        Long result = StreamSupport.stream(longRange, true)
                .filter(x -> x % 5 != 0)
                .filter(x -> x % 7 != 0)
                .reduce(0L, (x, y) -> x + y);
        System.out.println(result);
    }
}

class LongRange implements OfLong {
    private long from;
    private long to;

    LongRange(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public OfLong trySplit() {
//        if (to - from > 1) {
//            return new LongRange(from, from = ((from + to)) >>> 1);
//        }
        return null;
    }

    @Override
    public long estimateSize() {
        return to - from;
    }

    @Override
    public int characteristics() {
        return DISTINCT | IMMUTABLE | NONNULL | ORDERED | SIZED | SORTED | SUBSIZED;
    }

    @Override
    public boolean tryAdvance(LongConsumer consumer) {
        if (to > from) {
            consumer.accept(from++);
            return true;
        }
        return false;
    }
}


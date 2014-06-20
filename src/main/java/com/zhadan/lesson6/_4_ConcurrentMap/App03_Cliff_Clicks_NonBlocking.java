package com.zhadan.lesson6._4_ConcurrentMap;

public class App03_Cliff_Clicks_NonBlocking {
    public static void main(String[] args) {
        // INFO
        // -----------------
        // http://www.azulsystems.com/events/javaone_2008/2008_CodingNonBlock.pdf
        // http://www.stanford.edu/class/ee380/Abstracts/070221_LockFreeHash.pdf
        // http://www.azulsystems.com/events/javaone_2007/2007_LockFreeHash.pdf
        // http://www.infoq.com/news/2008/05/click_non_blocking
        // http://www.azulsystems.com/blog/cliff/2007-03-26-non-blocking-hashtable

        // SOURCE CODE
        // -----------------
        // http://sourceforge.net/projects/high-scale-lib/

        // Non-blocking HIERARCHY
        // -----------------
        // http://en.wikipedia.org/wiki/Non-blocking_algorithm
        // http://ru.wikipedia.org/wiki/%D0%9D%D0%B5%D0%B1%D0%BB%D0%BE%D0%BA%D0%B8%D1%80%D1%83%D1%8E%D1%89%D0%B0%D1%8F_%D1%81%D0%B8%D0%BD%D1%85%D1%80%D0%BE%D0%BD%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F#.D0.A2.D1.80.D0.B8_.D1.83.D1.80.D0.BE.D0.B2.D0.BD.D1.8F_.D0.BD.D0.B5.D0.B1.D0.BB.D0.BE.D0.BA.D0.B8.D1.80.D1.83.D1.8E.D1.89.D0.B5.D0.B9_.D1.81.D0.B8.D0.BD.D1.85.D1.80.D0.BE.D0.BD.D0.B8.D0.B7.D0.B0.D1.86.D0.B8.D0.B8

        // throughput VS latency/jitter

        // fairness HIERARCHY
        // -----------------
        // A policy that determines which action gets to execute next---namely,
        // the order in which processes execute.
        // A scheduling policy is UNCONDITIONALLY FAIR if unconditional
        // atomic actions eventually get to execute.
        // It is WEAKLY FAIR if conditional atomic actions eventually get
        // to execute if the delay condition becomes true and remains true.
        // It is STRONGLY FAIR if conditional atomic actions eventually get
        // to execute if the delay condition is infinitely often true.
        // BOOK: http://www.amazon.com/Foundations-Multithreaded-Parallel-Distributed-Programming/dp/0201357526
        // BOOK: http://www.ozon.ru/context/detail/id/1372271/
        // GLOSSARY: http://www.cs.arizona.edu/~greg/mpdbook/glossary.html
    }
}

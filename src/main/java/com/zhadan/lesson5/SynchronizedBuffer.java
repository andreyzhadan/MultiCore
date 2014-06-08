package com.zhadan.lesson5;

/**
 * Created by andrewzhadan on 6/7/14.
 */
// Goetz. Java concurrency in practice. EN - page 180-200
public class SynchronizedBuffer<T> {
    private final Object lock = new Object(); // private mutex
    private T elem = null;

    // BLOCKS-UNTIL: empty
    public void put(T newElem) throws InterruptedException {
        synchronized (lock) {
            while (elem != null) {
                lock.wait();
            }
            elem = newElem;
            lock.notifyAll();
        }
    }

    // BLOCKS-UNTIL: full
    public T take() throws InterruptedException {
        synchronized (lock) {
            while (elem == null) {
                lock.wait();
            }
            T tmp = elem;
            elem = null;
            lock.notifyAll();
            return tmp;
        }
    }
}


class App00_1x0 {
    public static void main(String[] args) throws InterruptedException {
        final SynchronizedBuffer<Character> buffer = new SynchronizedBuffer<>();
        // PRODUCER
        for (char c = 'A'; c <= 'Z'; c++) {
            buffer.put(c);
            System.err.println(c + "->");
        }
    }
}


class App00_3x0 {
    public static void main(String[] args) throws InterruptedException {
        final SynchronizedBuffer<String> buffer = new SynchronizedBuffer<>();
        // WRITER
        for (int k = 0; k < 3; k++) {
            final int finalK = k;
            new Thread(new Runnable() {
                public void run() {
                    for (char c = 'A'; c <= 'Z'; c++) {
                        String value = "" + c + finalK;
                        System.err.println(value + ".");
                        try {
                            buffer.put(value);
                        } catch (InterruptedException ignore) {/*NOP*/}
                        System.err.println(value + "->");
                    }
                }
            }).start();
        }
    }
}


class App00_3x1 {
    public static void main(String[] args) throws InterruptedException {
        final SynchronizedBuffer<String> buffer = new SynchronizedBuffer<>();
        // PRODUCER
        for (int k = 0; k < 3; k++) {
            final int finalK = k;
            new Thread(new Runnable() {
                public void run() {
                    for (char c = 'A'; c <= 'Z'; c++) {
                        String value = "" + c + finalK;
                        try {
                            buffer.put(value);
                        } catch (InterruptedException ignore) {/*NOP*/}
                        System.err.println(value + "->");
                    }
                }
            }).start();
        }

        // CONSUMER
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        String value = buffer.take();
                        System.err.println("    ->" + value);
//                        Thread.sleep(300);
                    } catch (InterruptedException ignore) {/*NOP*/}
                }
            }
        }).start();
    }
}
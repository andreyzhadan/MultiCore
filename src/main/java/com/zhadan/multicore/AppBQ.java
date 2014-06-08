package com.zhadan.multicore;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by andrewzhadan on 5/1/14.
 */
public class AppBQ {
    public static void main(String[] args) {
        final BlockingQueue<Character> buffer = new SynchronousQueue<Character>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (char c = 'A'; c <= 'z'; c++) {
                    try {
                        buffer.put(c);
                        System.out.println(c + " -> ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}

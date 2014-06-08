package com.zhadan.lesson2;

// может работать неограниченно долго и напечатать 0 или 1
public class App12 {
    static int x = 0;
    static int y = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread newThread = new Thread(new Runnable() {
            public void run() {
                x = 1;
                y = 1;
            }
        });
        newThread.start();

//        newThread.join(); // БЕЗ join
        while (y == 0); // 0 .. inf
        System.out.println(" x=" + x); // 0, 1
    }
}

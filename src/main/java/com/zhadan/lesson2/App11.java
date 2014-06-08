package com.zhadan.lesson2;

// может напечатать любую из комбинаций 00 01 10 11
public class App11 {
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
        System.out.print(y); // 0, 1
        System.out.print(x); // 0, 1
    }
}

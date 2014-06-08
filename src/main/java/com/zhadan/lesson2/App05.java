package com.zhadan.lesson2;

// может напечатать любую из комбинаций: 00 01 10 11
public class App05 {
    static int x = 0;
    static int y = 0;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                System.out.print(y); // 0, 1
                System.out.print(x); // 0, 1
            }
        }).start();
        x = 1;
        y = 1;
    }
}

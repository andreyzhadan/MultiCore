package com.zhadan.golovach.lesson2;

public class App06 {
    static int x = 0;
    static int y = 0;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                while (y == 0); // 0 .. inf
                System.out.println(x); // 0, 1
            }
        }).start();
        x = 1;
        y = 1;
    }
}

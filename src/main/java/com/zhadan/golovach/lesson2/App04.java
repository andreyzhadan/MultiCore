package com.zhadan.golovach.lesson2;

// гарантированно остановится и напечатает 1
public class App04 {
    static int x = 0;
    static int y = 0;
    public static void main(String[] args) {
        x = 1;
        y = 1;
        new Thread(new Runnable() {
            public void run() {
                while (y == 0); // 0 .. N
                System.out.println(x); // 1
            }
        }).start();
    }
}

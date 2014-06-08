package com.zhadan.lesson2;

// гарантированно остановится
public class App01_1 {
    static int x = 0;
    public static void main(String[] args) {
        x = 1;
        new Thread(new Runnable() {
            public void run() {
                while (x == 0); // 0 .. N
            }
        }).start();
    }
}

package com.zhadan.golovach.lesson2;

// гарантированно напечатает 1 1
public class App03 {
    static int x = 0;
    static int y = 0;
    public static void main(String[] args) {
        x = 1;
        y = 1;
        new Thread(new Runnable() {
            public void run() {
                System.out.println(y); // 1
                System.out.println(x); // 1
            }
        }).start();
    }
}

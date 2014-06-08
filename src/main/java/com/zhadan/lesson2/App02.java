package com.zhadan.lesson2;

// но тут может работать неограниченно долго
public class App02 {
    static int x = 0;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                while (x == 0); // 0 .. inf
            }
        }).start();
        x = 1;
    }
}

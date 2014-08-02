package com.zhadan.golovach.lesson2;

// может напечатать только 1
public class App00_2 {
    static int x = 0;
    public static void main(String[] args) {
        x = 1;
        new Thread(new Runnable() {
            public void run() {
                System.out.println(x); // 1
            }
        }).start();
    }
}

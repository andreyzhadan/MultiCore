package com.zhadan.golovach.lesson2;

// НЕТ "out of thin air" == "из воздуха"
public class App00_1 {
    static int x = 0;
    static int y = 0;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                if (x == 1) {
                    y = 1;
                }
            }
        }).start();

        if (y == 1) {
            x = 1;
        }

        System.out.println(x); // только 0
        System.out.println(y); // только 0
    }
}

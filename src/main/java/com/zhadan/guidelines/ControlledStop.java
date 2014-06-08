package com.zhadan.guidelines;

/**
 * Created by andrewzhadan on 6/8/14.
 */
final class ControlledStop implements Runnable {
    private volatile boolean done = false; //AtomicBoolean

    @Override
    public void run() {
        while (!done) { //done.get()
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Get");
    }

    public void shutdown() {
        System.out.println("Set");
        done = true; //done.set(true)
    }
}

final class ControlledStop2 implements Runnable {
    private boolean done = false;

    @Override
    public void run() {
        while (!isDone()) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Get");
    }

    public synchronized void shutdown() {
        System.out.println("Set");
        done = true;
    }

    public synchronized boolean isDone() {
        System.out.println("isDone");
        return done;
    }
}

class Main {
    public static void main(String[] args) {
        //1-st example
        ControlledStop controlledStop = new ControlledStop();
        controlledStop.shutdown();
        new Thread(controlledStop).start();

        //2-nd example
        ControlledStop2 controlledStop2 = new ControlledStop2();
        controlledStop2.shutdown();
        new Thread(controlledStop2).start();
    }
}

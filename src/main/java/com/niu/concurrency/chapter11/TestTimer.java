package com.niu.concurrency.chapter11;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Test Timer
 *
 * @author [nza]
 * @version 1.0 [2020/09/03 15:27]
 * @createTime [2020/09/03 15:27]
 */
public class TestTimer {

    static Timer timer = new Timer();

    public static void main(String[] args) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task1 run");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    throw new RuntimeException();
                }
            }
        }, 500);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("task2 run");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 1000);
    }
}

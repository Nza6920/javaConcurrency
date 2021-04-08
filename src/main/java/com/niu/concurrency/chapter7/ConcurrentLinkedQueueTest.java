package com.niu.concurrency.chapter7;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue Test
 *
 * @author [nza]
 * @version 1.0 [2020/08/25 14:41]
 * @createTime [2020/08/25 14:41]
 */
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        Thread offerThread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("添加元素: " + i);
                queue.add(i);
            }
        });

        Thread offerThread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("添加元素2: " + i);
                queue.add(i);
            }
        });

        offerThread1.start();
        offerThread2.start();
    }
}

package com.niu.concurrency.chapter7;

import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue Test
 *
 * @author [nza]
 * @version 1.0 [2020/08/25 14:19]
 * @createTime [2020/08/25 14:19]
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(100000);

        // 入队 和 出队可以同时操作
        Thread putThread = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 100000; i > 0; i--) {
                try {
//                    System.out.print("put元素: " + i);
                    blockingQueue.put(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("入队耗时: " + (end - start) + "ms");
        });

        Thread takeThread = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 100000; i > 0; i--) {
                try {
                    String take = blockingQueue.take();
//                    System.out.print("获取元素: " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("出队耗时: " + (end - start) + "ms");
        });

        Thread offerThread = new Thread(() -> {
            for (int i = 100; i > 0; i--) {
                System.out.print("put元素: " + i);
                blockingQueue.offer(String.valueOf(i));
            }
        });

        Thread pollThread = new Thread(() -> {
            for (int i = 100; i > 0; i--) {
                String take = blockingQueue.poll();
                System.out.println("获取元素: " + take);
            }
        });

        Thread removeThread = new Thread(() -> {
            for (int i = 100; i > 0; i--) {
                System.out.println("删除元素: " + i);
                blockingQueue.remove(String.valueOf(i));
            }
        });

        long start = System.currentTimeMillis();
        putThread.start();
        takeThread.start();
        putThread.join();
        takeThread.join();
        long end = System.currentTimeMillis();
//        removeThread.start();
//
//        Thread.sleep(100);
//
//        offerThread.start();
//        pollThread.start();

        System.out.println("\nfinish: " + ((end - start) / 1000) + "s");
    }
}

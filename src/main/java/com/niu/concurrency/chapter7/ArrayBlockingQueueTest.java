package com.niu.concurrency.chapter7;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue Test
 *
 * @version 1.0 [2020/08/25 15:20]
 * @author [nza]
 * @createTime [2020/08/25 15:20]
 */
public class ArrayBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(100000);

        // 入队 和 出队可以同时操作
        Thread putThread = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 100000; i > 0; i--) {
                try {
//                    System.out.print("put元素: " + i);
                    queue.put(String.valueOf(i));
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
                    String take = queue.take();
//                    System.out.print("获取元素: " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("出队耗时: " + (end - start) + "ms");
        });

        long start = System.currentTimeMillis();
        putThread.start();
        takeThread.start();

        putThread.join();
        takeThread.join();
        long end = System.currentTimeMillis();

        System.out.println("\nfinish: " + ((end - start) / 1000) + "s");
    }
}

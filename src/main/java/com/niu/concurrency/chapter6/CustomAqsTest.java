package com.niu.concurrency.chapter6;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

/**
 * CustomAqsTest
 *
 * @author [nza]
 * @version 1.0 [2020/08/24 16:29]
 * @createTime [2020/08/24 16:29]
 */
public class CustomAqsTest {

    final static CustomAqs lock = new CustomAqs();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();

    final static Queue<String> queue = new LinkedBlockingQueue<String>();
    final static int queueSize = 1;

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            while (true) {
                // 获取独占锁
                lock.lock();
                try {
                    // 如果队列满了则等待
                    while (queue.size() == queueSize) {
                        notEmpty.await();
                    }
                    System.out.println("生产");
                    queue.add("ele");

                    // 唤醒消费线程
                    notFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        notFull.await();
                    }

                    // 消费一个元素
                    String ele = queue.poll();
                    System.out.println("消费: " + ele);
                    // 唤醒生产线程
                    notEmpty.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        consumer.start();
        Thread.sleep(1000);
        producer.start();
    }
}

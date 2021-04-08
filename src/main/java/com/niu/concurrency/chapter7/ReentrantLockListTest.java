package com.niu.concurrency.chapter7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLockList Test
 *
 * @author [nza]
 * @version 1.0 [2020/08/25 10:06]
 * @createTime [2020/08/25 10:06]
 */
public class ReentrantLockListTest {

    private List<String> list = new ArrayList<>();

    // 锁
    private final ReentrantLock lock = new ReentrantLock();

    // 添加元素
    public void add(String ele) {
        lock.lock();
        try {
            list.add(ele);
        } finally {
            lock.unlock();
        }
    }

    // 删除元素
    public void delete(String ele) {
        lock.lock();
        try {
            list.remove(ele);
        } finally {
            lock.unlock();
        }
    }

    // 获取数据
    public String get(int index) {
        lock.lock();
        try {
            return list.get(index);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockListTest listTest = new ReentrantLockListTest();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println("添加元素");
                listTest.add("元素: " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println("获取元素: " + listTest.get(i));
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println("获取元素2: " + listTest.get(i));
            }
        });

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println("获取元素3: " + listTest.get(i));
            }
        });

        Thread t5 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println("获取元素4: " + listTest.get(i));
            }
        });

        Thread t6 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println("获取元素5: " + listTest.get(i));
            }
        });

        long start = System.currentTimeMillis();
        t1.start();
        t1.join();
        long end = System.currentTimeMillis();
        long writeTime = end - start;

        start = System.currentTimeMillis();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        end = System.currentTimeMillis();
        long readTime = end - start;


        System.out.println("写数据花费时间: " + writeTime + "ms");
        System.out.println("读取数据花费时间: " + readTime + "ms");
    }
}

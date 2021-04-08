package com.niu.concurrency.chapter6;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport Test
 *
 * @version 1.0 [2020/08/24 10:40]
 * @author [nza]
 * @createTime [2020/08/24 10:40]
 */
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("begin park!");
//
//        LockSupport.park();
//
//        System.out.println("end park!");

        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park");

            // 调用park方法, 挂起自己
            LockSupport.park();

            System.out.println("child thread unPark");
        });

        // 启动子线程
        thread.start();

        Thread.sleep(1000);

        System.out.println("main thread begin unPark");

        LockSupport.unpark(thread);
    }
}

package com.niu.concurrency.chapter6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition Test
 *
 * @version 1.0 [2020/08/24 14:09]
 * @author [nza]
 * @createTime [2020/08/24 14:09]
 */
public class ConditionTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("begin wait");

                condition.await();

                System.out.println("end wait");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        Thread.sleep(1000);
        lock.lock();
        try {
            System.out.println("begin single");

            condition.signal();

            System.out.println("end single");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

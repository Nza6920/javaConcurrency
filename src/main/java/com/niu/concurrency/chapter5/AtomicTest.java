package com.niu.concurrency.chapter5;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子类测试
 *
 * @author [nza]
 * @version 1.0 [2020/08/21 10:12]
 * @createTime [2020/08/21 10:12]
 */
public class AtomicTest {

    private static AtomicLong atomicLong = new AtomicLong();

    private static final Integer[] arrayOnes = new Integer[]{0, 1, 2, 3, 0, 10, 0, 11, 0};
    private static final Integer[] arrayTwos = new Integer[]{10, 1, 2, 3, 0, 10, 0, 11, 0};

    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                int sizeOf = arrayOnes.length;

                for (int i = 0; i < sizeOf; ++i) {
                    if (arrayOnes[i].intValue() == 0) {
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                int sizeOf = arrayTwos.length;

                for (int i = 0; i < sizeOf; ++i) {
                    if (arrayTwos[i].intValue() == 0) {
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        // 启动线程
        one.start();
        two.start();

        // 等待线程执行完毕
        one.join();
        two.join();

        System.out.println(atomicLong.get());
    }
}

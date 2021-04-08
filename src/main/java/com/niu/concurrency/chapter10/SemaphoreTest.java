package com.niu.concurrency.chapter10;

import java.util.concurrent.*;

/**
 * 信号量测试
 *
 * @author [nza]
 * @version 1.0 [2020/08/28 10:07]
 * @createTime [2020/08/28 10:07]
 */
public class SemaphoreTest {

    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                2,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                (ThreadFactory) Thread::new);

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " over");
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " over");
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        semaphore.acquire(2);
        System.out.println("main is over");

        executorService.shutdown();
    }
}

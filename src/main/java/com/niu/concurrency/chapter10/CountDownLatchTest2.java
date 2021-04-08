package com.niu.concurrency.chapter10;

import java.util.concurrent.*;

/**
 * CountDownLatch Test
 *
 * @author [nza]
 * @version 1.0 [2020/08/27 13:09]
 * @createTime [2020/08/27 13:09]
 */
public class CountDownLatchTest2 {

    // 创建 CountDownLatch 实例
    private static volatile CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(
                3,
                3,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                (ThreadFactory) Thread::new);

        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        System.out.println("start all child thread over");

        System.out.println("wait all child thread over");

        countDownLatch.await();

        System.out.println("all child thread over");

        executorService.shutdown();
    }
}

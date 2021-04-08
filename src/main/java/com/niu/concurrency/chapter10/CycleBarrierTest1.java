package com.niu.concurrency.chapter10;

import java.util.concurrent.*;

/**
 * 回环屏障测试
 *
 * @author [nza]
 * @version 1.0 [2020/08/27 14:19]
 * @createTime [2020/08/27 14:19]
 */
public class CycleBarrierTest1 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println(Thread.currentThread() + " task1 merge result"));

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                2,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                (ThreadFactory) Thread::new);

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " task1-1");

                System.out.println(Thread.currentThread() + " enter in barrier");

                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " enter out barrier");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " task1-2");

                System.out.println(Thread.currentThread() + " enter in barrier");

                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " enter out barrier");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}

package com.niu.concurrency.chapter10;

import java.util.concurrent.*;

/**
 * 回环屏障测试
 *
 * @author [nza]
 * @version 1.0 [2020/08/27 14:19]
 * @createTime [2020/08/27 14:19]
 */
public class CycleBarrierTest2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println(Thread.currentThread() + " task merge result"));

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                2,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                (ThreadFactory) Thread::new);

        // 线程A
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " step1");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + " step2");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step3");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 线程B
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " step1");
                cyclicBarrier.await();

                Thread.sleep(2000);
                System.out.println(Thread.currentThread() + " step2");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step3");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}

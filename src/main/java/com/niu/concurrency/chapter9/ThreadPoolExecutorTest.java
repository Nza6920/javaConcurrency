package com.niu.concurrency.chapter9;

import com.niu.concurrency.chapter8.MyThreadFactory;

import java.util.concurrent.*;

/**
 * ThreadPoolExecutor Test
 *
 * @author [nza]
 * @version 1.0 [2020/08/27 10:13]
 * @createTime [2020/08/27 10:13]
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyThreadFactory threadFactory = new MyThreadFactory(false);
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(10,
                20,
                1,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(),
                threadFactory);
        try {
            for (int i = 0; i < 1000; i++) {
                Future<String> res = executorService.submit(new TestRunnable(String.valueOf(i)));
                System.out.println(res.get());
            }
        } finally {
            executorService.shutdown();
        }
    }

    static class TestRunnable implements Callable<String> {

        private final String msg;

        public TestRunnable(String msg) {
            this.msg = msg;
        }

        @Override
        public String call() throws Exception {
            return Thread.currentThread() + ", task [" + msg + "] is finished";
        }
    }
}

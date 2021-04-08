package com.niu.concurrency.chapter9;

import com.niu.concurrency.chapter8.MyThreadFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor Test
 *
 * @author [nza]
 * @version 1.0 [2020/08/27 10:55]
 * @createTime [2020/08/27 10:55]
 */
public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) {
        ThreadFactory threadFactory;
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(
                10,
                new MyThreadFactory(false));

//        for (int i = 0; i < 10000; i++) {
//            executor.schedule(new TestRunnable(String.valueOf(i)), 10, TimeUnit.SECONDS);
//        }

//        for (int i = 0; i < 10000; i++) {
//            executor.scheduleAtFixedRate(new TestRunnable(String.valueOf(i)), 10, 10, TimeUnit.SECONDS);
//        }

        for (int i = 0; i < 10000; i++) {
            executor.scheduleWithFixedDelay(new TestRunnable(String.valueOf(i)), 10, 10, TimeUnit.SECONDS);
        }
    }

    static class TestRunnable implements Runnable {

        private final String msg;

        public TestRunnable(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            System.out.println("task:" + msg);
        }
    }
}

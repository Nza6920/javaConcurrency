package com.niu.concurrency.chapter8;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义线程工厂
 *
 * @author [nza]
 * @version 1.0 [2020/08/27 10:24]
 * @createTime [2020/08/27 10:24]
 */
public class MyThreadFactory implements ThreadFactory {

    private final boolean isDaemon;

    public MyThreadFactory(boolean isDaemon) {
        this.isDaemon = isDaemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new MyWorkThread(r);
        thread.setDaemon(isDaemon);
        return thread;
    }
}

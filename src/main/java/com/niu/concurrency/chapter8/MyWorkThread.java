package com.niu.concurrency.chapter8;

/**
 * 自定义工作线程
 *
 * @author [nza]
 * @version 1.0 [2020/08/27 10:21]
 * @createTime [2020/08/27 10:21]
 */
public class MyWorkThread extends Thread {

    public MyWorkThread(Runnable runnable) {
        super(runnable);
    }

    @Override
    public void run() {
        System.out.println("name: " + this.getName());
        super.run();
    }
}

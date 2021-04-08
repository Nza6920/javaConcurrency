package com.niu.concurrency.chapter11;

import java.text.SimpleDateFormat;

/**
 * SampleDateFormat 线程不安全
 *
 * @author [nza]
 * @version 1.0 [2020/09/03 15:11]
 * @createTime [2020/09/03 15:11]
 */
public class TestSampleDateFormat {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {
        // 创建多个线程并且启动
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
//                    synchronized (sdf) {
//                        sdf.parse("2019-11-11 11:11:11");
//                    }
                    threadLocal.get().parse("2019-11-11 11:11:11");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    threadLocal.remove();
                }
            });
            thread.start();
        }
    }
}

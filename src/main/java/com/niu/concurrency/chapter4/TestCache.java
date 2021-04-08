package com.niu.concurrency.chapter4;

/**
 * 测试伪共享
 *
 * @author [nza]
 * @version 1.0 [2020/08/20 16:19]
 * @createTime [2020/08/20 16:19]
 */
public class TestCache {

    static final int LINE_NUMBER = 1024;

    static final int COLUMN_NUM = 1024;

    public static void main(String[] args) {

        for (int i = 10; i > 0; i--) {
            test1();
            test2();
        }

    }

    public static void test1() {
        long[][] array = new long[LINE_NUMBER][COLUMN_NUM];

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUM; j++) {
                array[i][j] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        long cacheTime = endTime - startTime;
        System.out.println("cache time: " + cacheTime);
    }

    public static void test2() {

        long[][] array = new long[LINE_NUMBER][COLUMN_NUM];

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUM; j++) {
                array[j][i] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        long cacheTime = endTime - startTime;
        System.out.println("cache2 time: " + cacheTime);
    }
}

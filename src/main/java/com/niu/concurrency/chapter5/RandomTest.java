package com.niu.concurrency.chapter5;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数生成测试类
 *
 * @author [nza]
 * @version 1.0 [2020/08/21 09:39]
 * @createTime [2020/08/21 09:39]
 */
public class RandomTest {
    public static void main(String[] args) {
        // 获取一个随机数生成器
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        // 输出随机数
        for (int i = 0; i < 10; ++i) {
            System.out.println(threadLocalRandom.nextInt(5));
        }
    }
}

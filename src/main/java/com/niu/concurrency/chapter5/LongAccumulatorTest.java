package com.niu.concurrency.chapter5;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * LongAccumulator Test
 *
 * @author [nza]
 * @version 1.0 [2020/08/21 13:37]
 * @createTime [2020/08/21 13:37]
 */
public class LongAccumulatorTest {
    public static void main(String[] args) {
        LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 1);
        longAccumulator.accumulate(2);
        System.out.println(longAccumulator.get());
    }
}

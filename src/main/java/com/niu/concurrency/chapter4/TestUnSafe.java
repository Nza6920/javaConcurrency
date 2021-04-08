package com.niu.concurrency.chapter4;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 测试 Unsafe 类
 *
 * @author [nza]
 * @version 1.0 [2020/08/20 10:15]
 * @createTime [2020/08/20 10:15]
 */
public class TestUnSafe {

    /**
     * unsafe 实例
     */
    private static final Unsafe unsafe;

    /**
     * state 在 TestUnSafe 中的偏移量
     */
    private final static long stateOffset;

    /**
     * 变量
     */
    private volatile long state = 0;

    static {
        try {

            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            stateOffset = unsafe.objectFieldOffset(TestUnSafe.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        TestUnSafe testUnSafe = new TestUnSafe();
        System.out.println(testUnSafe.state);
        Boolean successful = unsafe.compareAndSwapInt(testUnSafe, stateOffset, 0, 1);
        System.out.println(successful);
        System.out.println(testUnSafe.state);
    }
}

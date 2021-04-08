package com.niu.concurrency.chapter7;

import java.util.concurrent.locks.StampedLock;

/**
 * 点
 *
 * @author [nza]
 * @version 1.0 [2020/08/25 11:08]
 * @createTime [2020/08/25 11:08]
 */
public class Point {

    private double x, y;

    private final StampedLock lock = new StampedLock();

    // 写锁
    public void move(double deltaX, double deltaY) {
        long stamp = lock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    // 乐观读锁
    public double distanceFromOrigin() {
        // 获取乐观锁
        long stamp = lock.tryOptimisticRead();
        // 将全部变量复制到方法体栈内
        double currentX = x, currentY = y;
        // 检查stamp的值在这期间是否仍然合法
        if (!lock.validate(stamp)) {
            // 如果不合法, 获取一个悲观的读锁
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp);
            }
        }

        // 计算结果
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // 使用悲观锁获取读锁, 并尝试转换为写锁
    public void moveIfAtOrigin(Double newX, Double newY) {
        long stamp = lock.readLock();
        try {
            // 如果当前的点在原点则移动该点
            while (new Double(x).equals(0.0) && new Double(y).equals(0.0)) {
                // 尝试将读锁转换为写锁
                long writeStamp = lock.tryConvertToWriteLock(stamp);
                // 成功
                if (writeStamp != 0L) {
                    stamp = writeStamp;
                    x = newX;
                    y = newY;
                } else {
                    // 失败, 释放读锁, 显式获取写锁
                    lock.unlockRead(stamp);
                    stamp = lock.writeLock();
                }
            }
        } finally {
            lock.unlock(stamp);
        }
    }

    public static void main(String[] args) {
        Point point = new Point();

        point.move(0, 0);

        point.moveIfAtOrigin(3.0, 4.0);

        double distance = point.distanceFromOrigin();
        System.out.println(distance);

        point.move(0, 0);
    }
}

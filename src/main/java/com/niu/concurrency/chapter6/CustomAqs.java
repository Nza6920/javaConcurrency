package com.niu.concurrency.chapter6;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * AQS - 自定义
 *
 * @author [nza]
 * @version 1.0 [2020/08/24 14:38]
 * @createTime [2020/08/24 14:38]
 */
public class CustomAqs implements Lock, Serializable {

    // 内部帮助类
    private static class Sync extends AbstractQueuedSynchronizer {

        // 是否锁已被持有
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 如果 state == 0 尝试获取锁
        @Override
        protected boolean tryAcquire(int acquire) {
            assert acquire == 1;

            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int release) {
            assert release == 1;
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }
}

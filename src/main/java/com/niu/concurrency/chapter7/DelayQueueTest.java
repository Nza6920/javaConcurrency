package com.niu.concurrency.chapter7;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列测试
 *
 * @author [nza]
 * @version 1.0 [2020/08/25 16:45]
 * @createTime [2020/08/25 16:45]
 */
public class DelayQueueTest {

    public static class DelayEle implements Delayed {

        private final long delayTime;

        private final long expire;

        private final String taskName;

        public DelayEle(long delayTime, String taskName) {
            this.delayTime = delayTime;
            this.taskName = taskName;
            this.expire = System.currentTimeMillis() + delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("delay=").append(delayTime).append("ms");
            sb.append(", expireAt=").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(expire)));
            sb.append(", taskName='").append(taskName).append('\'');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        DelayQueue<DelayEle> queue = new DelayQueue<>();

        Random random = new Random();
        for (int i = 0; i < 3000; i++) {
            DelayEle ele = new DelayEle(random.nextInt(180000), "task: " + i);
            queue.offer(ele);
        }

        DelayEle ele = null;
        try {
            for (; ; ) {
                System.out.println("剩余: " + queue.size());
                if (queue.size() == 0) {
                    break;
                }
                System.out.println(queue.take().toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

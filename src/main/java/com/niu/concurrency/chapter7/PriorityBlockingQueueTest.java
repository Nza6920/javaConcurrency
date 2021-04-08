package com.niu.concurrency.chapter7;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue Test
 *
 * @author [nza]
 * @version 1.0 [2020/08/25 16:09]
 * @createTime [2020/08/25 16:09]
 */
public class PriorityBlockingQueueTest {

    public static class Task implements Comparable<Task> {

        public Task(Integer priority, String taskName) {
            this.priority = priority;
            this.taskName = taskName;
        }

        private Integer priority = 0;

        private String taskName;

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public int compareTo(Task o) {
            if (this.priority >= o.getPriority()) {
                return -1;
            }
            return 1;
        }

        public void doSomething() {
            System.out.println(taskName + ":" + priority);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        Thread addThread = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 1000; ++i) {
                System.out.println("添加元素");
                Task task = new Task(random.nextInt(1000), "taskName" + i);
                queue.offer(task);
            }
        });

        Thread takeThread = new Thread(() -> {
            while (!queue.isEmpty()) {
                Task task = null;
                try {
                    task = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task != null) {
                    task.doSomething();
                }
            }
        });

        addThread.start();
        Thread.sleep(5000);
        takeThread.start();
    }
}

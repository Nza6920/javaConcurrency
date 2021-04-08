package com.niu.concurrency.chapter4;

/**
 * <功能简述>
 *
 * @version 1.0 [2020/08/20 10:43]
 * @author [nza]
 * @createTime [2020/08/20 10:43]
 */
public class TestVolatile {

    private static int num = 0;

    private static boolean ready = false;

    static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready) {
                    System.out.println(num + num);
                }
            }
        }
    }

    static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("write thread set over");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int time = 100;
        while (time-- > 0) {
            ReadThread readThread = new ReadThread();
            readThread.start();
            WriteThread writeThread = new WriteThread();
            writeThread.start();
            Thread.sleep(1000);
            readThread.interrupt();
            System.out.println("main exit" + time);
        }
    }
}

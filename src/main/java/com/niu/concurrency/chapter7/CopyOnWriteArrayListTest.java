package com.niu.concurrency.chapter7;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList Test
 *
 * @author [nza]
 * @version 1.0 [2020/08/24 10:12]
 * @createTime [2020/08/24 10:12]
 */
public class CopyOnWriteArrayListTest {

    private static volatile CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        arrayList.add("Hello0");
        arrayList.add("Hello1");
        arrayList.add("Hello2");
        arrayList.add("Hello3");
        arrayList.add("Hello4");
        arrayList.add("Hello5");
        arrayList.add("Hello6");


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 修改下标为1的元素为niuniu
                arrayList.set(1, "niuniu");
                // 删除元素
                arrayList.remove(0);
                arrayList.remove(2);
            }
        });

        // 保证在修改线程启动前获取迭代器
        Iterator<String> iterator = arrayList.iterator();

        // 启动修改线程
        thread.start();

        // 等待线程执行完毕
        thread.join();

        // 迭代元素
        System.out.println("第一次迭代");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 迭代元素
        iterator = arrayList.iterator();
        System.out.println("第二次迭代");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

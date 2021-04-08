package com.niu.concurrency.chapter11;

import cn.hutool.json.JSONUtil;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ConcurrentHashMap 使用注意事项
 *
 * @author [nza]
 * @version 1.0 [2020/09/03 14:40]
 * @createTime [2020/09/03 14:40]
 */
public class TestMap {
    private static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        // 进入直播间topic1, 线程one
        Thread threadOne = new Thread(() -> {
            List<String> list1 = new CopyOnWriteArrayList<>();
            list1.add("device1");
            list1.add("device2");

            List<String> ab = map.putIfAbsent("topic1", list1);
            if (ab != null) {
                ab.addAll(list1);
            }
            System.out.println(JSONUtil.toJsonPrettyStr(map));
        });

        // 进入直播间topic1 线程Two
        Thread threadTwo = new Thread(() -> {
            List<String> list2 = new CopyOnWriteArrayList<>();
            list2.add("device11");
            list2.add("device22");

            List<String> ab = map.putIfAbsent("topic1", list2);
            if (ab != null) {
                ab.addAll(list2);
            }
            System.out.println(JSONUtil.toJsonPrettyStr(map));
        });


        // 进入直播间topic2 线程Three
        Thread threadThree = new Thread(() -> {
            List<String> list2 = new CopyOnWriteArrayList<>();
            list2.add("device111");
            list2.add("device222");

            List<String> ab = map.putIfAbsent("topic2", list2);
            if (ab != null) {
                ab.addAll(list2);
            }
            System.out.println(JSONUtil.toJsonPrettyStr(map));
        });

        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }


}

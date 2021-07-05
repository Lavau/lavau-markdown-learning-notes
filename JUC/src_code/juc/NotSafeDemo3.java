package juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * description: 不安全类
 *
 * 1. 故障现象
 *      java.util.ConcurrentModifiedException
 * 2. 原因
 *      共享资源 list 没有加锁，导致多线程争抢
 * 3. 解决方法
 *      3.1  new Vector<>()
 *      3.2  Collections.synchronizedList(new ArrayList())
 *      3.3  new CopyOnWriteArrayList<>()
 * 4. 优化建议
 *      与时俱进，使用更高版本的方法解决问题
 *
 *
 * @author: Leet
 * @date: 2020-11-22 17:33
 **/
public class NotSafeDemo3 {
    public static void main(String[] args) {
        mapsafe();
    }

    public static void mapsafe() {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);}
            ).start();
        }
    }

    public static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);}
            ).start();
        }
    }

    /**
     * ctrl + alt + M 抽取方法的快捷键
     */
    public static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);}
            ).start();
        }
    }
}

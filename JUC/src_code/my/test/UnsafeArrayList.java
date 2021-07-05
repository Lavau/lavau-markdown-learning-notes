package my.test;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 不安全的 ArrayList
 *
 * @author: Leet
 * @date: 2020-11-22 17:19
 **/
public class UnsafeArrayList {
    public static void main(String[] args) {
        // list 为共享资源
        List<String> list = new ArrayList<>();

        // 循环 10000 次，每次创建一个新线程
        // 线程的工作（run() 执行的内容）是将当前线程的的线程名添加到 list 中
        for (int i = 0; i < 10000; i++) {
            //
            new Thread(() -> list.add(Thread.currentThread().getName())).start();
        }

        System.out.println(list.size());
    }

    public static void test01() {

    }
}

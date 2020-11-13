package synchronizedtest;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.ArrayList;
import java.util.List;

/**
 * description: synchronized 测试
 *
 * @author: Leet
 * @date: 2020-11-11 14:57
 **/
public class UnsafeList {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> list.add(Thread.currentThread().getName())).start();
        }
//        Thread.sleep(1000);
        System.out.println(list.size());
    }
}

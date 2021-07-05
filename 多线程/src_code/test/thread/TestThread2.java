package test.thread;

/**
 * description: 多线程学习2：实现 Runnable 接口实现多线程
 *
 * @author: Leet
 * @date: 2020-11-10 23:39
 **/
public class TestThread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            System.out.println(String.format("%s%d", "我是天才-----", i));
        }
    }

    public static void main(String[] args) {
        new Thread(new TestThread2()).start();

        for (int i = 0; i < 2000; i++) {
            System.out.println(String.format("%s%d", "不，我才是天才-----", i));
        }
    }
}

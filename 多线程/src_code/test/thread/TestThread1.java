package test.thread;

/**
 * description: 线程学习1：继承 Thread 类创建线程
 *
 * @author: Leet
 * @date: 2020-11-10 23:25
 **/
public class TestThread1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            System.out.println(String.format("%s%d", "我在看代码---", i));
        }
    }

    public static void main(String[] args) {

        new TestThread1().start();

        for (int i = 0; i < 2000; i++) {
            System.out.println(String.format("%s%d", "我在学习多线程---", i));
        }
    }
}

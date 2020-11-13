/**
 * description: 线程优先级测试
 *
 * @author: Leet
 * @date: 2020-11-11 11:26
 **/
public class TestPriority implements Runnable {
    @Override
    public void run() {
        System.out.printf("%S 的 priority : %d\n", Thread.currentThread().getName(), Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        System.out.printf("%S 的 priority : %d\n", Thread.currentThread().getName(), Thread.currentThread().getPriority());

        Thread thread1 = new Thread(new TestPriority(), "thread1");
        Thread thread2 = new Thread(new TestPriority(), "thread2");
        Thread thread3 = new Thread(new TestPriority(), "thread3");
        Thread thread4 = new Thread(new TestPriority(), "thread4");
        Thread thread5 = new Thread(new TestPriority(), "thread5");

        thread1.setPriority(1);
        thread2.setPriority(5);
        thread2.setPriority(9);
        thread4.setPriority(2);
        thread5.setPriority(10);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}

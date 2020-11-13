package test.thread;

/**
 * description: 线程学习4：模拟龟兔赛跑
 *
 * @author: Leet
 * @date: 2020-11-11 00:12
 **/
public class TestThread4 implements Runnable {

    // 共享的赛道
    private static int raceMeter = 100;
    private int racedMeter;

    @Override
    public void run() {
        while (racedMeter <= 100) {

            // rabit 跑到一半想要休息一下
            if ("rabit".equals(Thread.currentThread().getName()) && racedMeter == 50) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.printf("%s 已经跑了 %d 米%n", Thread.currentThread().getName(), racedMeter++);
        }
    }

    public static void main(String[] args) {
        new Thread(new TestThread4(), "turtle").start();
        new Thread(new TestThread4(), "rabit").start();

    }
}

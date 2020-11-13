package test.thread;

/**
 * description: 线程学习3：数据不同步问题
 *              模拟买票问题
 *
 * @author: Leet
 * @date: 2020-11-10 23:58
 **/
public class TestThread3 implements Runnable{

    /**
     * 一共 10 张票
     */
    private int picketNum = 10;

    @Override
    public void run() {
        while (true) {
            if (picketNum <= 0) {
                break;
            }

            // 模拟等待的情况
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("%s 拿到了第 %d 张票", Thread.currentThread().getName(), picketNum--));
        }
    }

    public static void main(String[] args) {
        TestThread3 testThread3 = new TestThread3();
        new Thread(testThread3, "张伟").start();
        new Thread(testThread3, "王海").start();
        new Thread(testThread3, "霍烨").start();
    }
}

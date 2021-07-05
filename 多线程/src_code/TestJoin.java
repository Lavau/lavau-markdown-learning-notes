/**
 * description: join() 方法测试
 *
 * @author: Leet
 * @date: 2020-11-11 11:06
 **/
public class TestJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.printf("%s%d\n", "线程 VIP 来了 ---》 ", i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TestJoin());
        thread.start();

        for (int i = 0; i < 500; i++) {
            if (i == 200) {
                thread.join();
            }

            System.out.printf("%s%d\n", "线程 main ---》 ", i);
        }
    }
}

package sleep;

/**
 * description: 线程状态学习：sleep()
 *              每个一秒打印一个数
 *
 * @author: Leet
 * @date: 2020-11-11 10:49
 **/
public class TestSleep1 {

    private static void printNum() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        printNum();
    }
}

package sleep;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:线程状态学习：sleep()
 *             时钟计时
 * @author: Leet
 * @date: 2020-11-11 10:52
 **/
public class TestSleep2 {
    private static void printTime() throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            long milis = System.currentTimeMillis();
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(milis)));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        printTime();
    }
}

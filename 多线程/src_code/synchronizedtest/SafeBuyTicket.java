package synchronizedtest;

/**
 * description: synchronized 测试
 *
 * @author: Leet
 * @date: 2020-11-11 15:10
 **/
public class SafeBuyTicket implements Runnable {
    private static Integer ticketNum = 10;

    @Override
    public void run() {
        while (ticketNum > 0) {
            synchronized (ticketNum) {
                System.out.printf("%s 拿到了第 %d 张票\n", Thread.currentThread().getName(), ticketNum--);

            }
        }
    }

    public static void main(String[] args) {
        new Thread(new synchronizedtest.UnsafeBuyTicket(), "A").start();
        new Thread(new synchronizedtest.UnsafeBuyTicket(), "B").start();
        new Thread(new synchronizedtest.UnsafeBuyTicket(), "C").start();
    }
}


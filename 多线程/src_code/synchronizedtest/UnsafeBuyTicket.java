package synchronizedtest;

/**
 * description: synchronized 测试
 * @author: Leet
 * @date: 2020-11-11 14:42
 **/
public class UnsafeBuyTicket implements Runnable {
    private static int ticketNum = 10;

    @Override
    public void run() {
        while (ticketNum > 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s 拿到了第 %d 张票\n", Thread.currentThread().getName(), ticketNum--);
        }
    }

    public static void main(String[] args) {
        new Thread(new UnsafeBuyTicket(), "A").start();
        new Thread(new UnsafeBuyTicket(), "B").start();
        new Thread(new UnsafeBuyTicket(), "C").start();
    }
}

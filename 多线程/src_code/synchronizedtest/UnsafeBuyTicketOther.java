package synchronizedtest;

/**
 * description: 多线程
 *
 * @author: Leet
 * @date: 2020-11-12 08:57
 **/
public class UnsafeBuyTicketOther {
    public static void main(String[] args) {
        BuyTicketOther station = new BuyTicketOther();

        new Thread(new BuyTicketOther(), "B").start();

        new Thread(new BuyTicketOther(), "A").start();
        new Thread(new BuyTicketOther(), "C").start();
    }
}



class BuyTicketOther implements Runnable {
    private static int ticketNums = 1000;
    boolean flag = true;

    @Override
    public void run() {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    /**
     * synchronized 所的是 this （不同的类实例对象this不同）
     * @throws InterruptedException
     */
    private static synchronized void buy() throws InterruptedException {
        while (ticketNums > 0 ) {
            Thread.sleep(100);
            System.out.printf("%s 拿到了第 %d 张票\n", Thread.currentThread().getName(), ticketNums--);
        }
    }
}

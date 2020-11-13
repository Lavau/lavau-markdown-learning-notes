package synchronizedtest;

/**
 * description: synchronized 测试
 *
 * @author: Leet
 * @date: 2020-11-11 14:51
 **/
public class UnsafeWithdrawMoney implements Runnable{

    private static int totalMoney = 500;

    private int money;

    public UnsafeWithdrawMoney(int money) {
        this.money = money;
    }

    @Override
    public void run() {
        if (totalMoney < money) {
            System.out.printf("%s 想要 %d, 金额不足，还剩 %d\n", Thread.currentThread().getName(), money, totalMoney);
            return;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        totalMoney = totalMoney - money;
        System.out.printf("%s 取了 %d, 还剩 %d\n", Thread.currentThread().getName(), money, totalMoney);
    }

    public static void main(String[] args) {
        new Thread(new UnsafeWithdrawMoney(200), "A").start();
        new Thread(new UnsafeWithdrawMoney(500), "B").start();
    }
}

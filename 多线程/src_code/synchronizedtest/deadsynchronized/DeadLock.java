package synchronizedtest.deadsynchronized;

/**
 * description: 验证死锁
 *    死锁：多个线程互相持有各自需要的资源，而僵持
 *    synchronized 块同时持有两个以上对象的锁，就可能造成死锁
 *
 * @author: Leet
 * @date: 2020-11-11 15:25
 **/

public class DeadLock {
    public static void main(String[] args) {
        // 灰姑娘想化妆，她拿起了口红
        new Thread(new Makeup(0, "灰姑娘")).start();
        // 白雪公主想化妆，她拿起了镜子
        new Thread(new Makeup(1, "白雪公主")).start();
    }
}

/**
 * 口红
 */
class Lipstick {}

/**
 * 镜子
 */
class Mirror {}

/**
 * 化妆
 */
class Makeup implements Runnable {

    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;
    String girlName;

    public Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * lipstick 与 mirror 是 Makeup 类的静态属性，为 Makeup 类对象持有；
     * lipstick 与 mirror只有一个，
     * 所以说，下方同步块中的代码，锁共享资源的方法正确
     */
    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.printf("%s 获得口红\n", girlName);
                Thread.sleep(1000);
            }
            synchronized (mirror) {
                System.out.printf("%s 获得镜子\n", girlName);
            }
        } else {
            synchronized (mirror) {
                System.out.printf("%s 获得镜子\n", girlName);
                Thread.sleep(2000);
            }
            synchronized (lipstick) {
                System.out.printf("%s 获得口红\n", girlName);
            }
        }
    }
}
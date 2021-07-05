/**
 * description: 守护线程
 *
 * @author: Leet
 * @date: 2020-11-11 14:15
 **/
public class TestDaemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new God());
        thread.setDaemon(true);
        thread.start();

        new Thread(new You()).start();
    }
}

class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("GOD BLESS YOU");
        }
    }
}

class You implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("You are happy every day");
        }
        System.out.println("----------Goodbye! The world----------");
    }
}

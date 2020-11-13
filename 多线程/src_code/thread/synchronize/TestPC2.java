package thread.synchronize;

/**
 * description: 生产者与消费者问题：信号灯法解决
 *
 * @author: Leet
 * @date: 2020-11-12 20:32
 **/
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

class Player extends Thread {
    TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.play("Hello World 播放中");
            } else {
                this.tv.play("抖音：记录记录美好生活");
            }
        }
    }
}

class Watcher extends Thread {
    TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        tv.watch();
    }
}

class TV {
    String voice;

    /**
     * true: 演员表演，观众等待
     * false: 观众观看，演员等待
     */
    boolean flag = true;

    public synchronized void play(String voice) {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("演员表演了 %s\n", voice);

        notifyAll();

        this.voice = voice;
        this.flag = !this.flag;
    }

    public synchronized void watch() {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("观众观看了 %s\n", voice);

        notifyAll();

        this.flag = !this.flag;
    }
}
package thread.synchronize;

/**
 * description: 生产者与消费者问题：管程法解决
 *
 * @author: Leet
 * @date: 2020-11-12 19:58
 **/
public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Productor(container).start();
        new Consumer(container).start();
    }
}

class Productor extends Thread {
    SynContainer container;

    public Productor(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                container.push(new Chicken(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("生产了 %d 只鸡\n", i);
        }
    }
}

class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                System.out.printf("消费了---> %d 只鸡\n", container.pop().id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Chicken {
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

class SynContainer {
    Chicken[] chickens = new Chicken[10];
    int count = 0;

    /**
     * 生产者放入产品
     * @param chicken 产品
     * @throws InterruptedException InterruptedException
     */
    public synchronized void push(Chicken chicken) throws InterruptedException {
        // 如果容器满了，需要等待消费者消费
        if (count == chickens.length) {
            wait();
        } else {
            // 容器没满，生产者直接放入产品
            chickens[count++] = chicken;

            // 通知消费者消费
            notifyAll();
        }
    }

    /**
     * 消费者消费
     * @return Chicken Chicken
     * @throws InterruptedException InterruptedException
     */
    public synchronized Chicken pop() throws InterruptedException {
        // 如果容器空了，消费者等待（等待生产者向容器中放入产品）
        if (count == 0) {
            this.wait();
        } {
            // 容器中有产品，消费者直接消费
            Chicken chicken = chickens[--count];

            // 告知生产者生产东西
            notifyAll();

            return chicken;
        }
    }
}
package my.test;

/**
 * description: 复习之前学过的线程协作 —— 管程法
 *      wait()
 *      notify() 与 notifyAll()
 *
 * @author: Leet
 * @date: 2020-11-22 21:02
 **/
public class ThreadCoorparation {
    public static void main(String[] args) throws InterruptedException {
        Chicken chicken = new Chicken();

        // 生产者
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                chicken.push(new Chicken(i));
                System.out.println("生产者生产了第\t" + i + "\t只鸡");;
            }
        }).start();

        // 消费者
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("消费者消费了第\t\t" + chicken.pop().id + "\t\t只鸡");;
            }
        }).start();
    }
}

/**
 * 产品 —— 鸡
 */
class Chicken {
    // 生产的鸡的编号
    int id;
    // 储藏室
    // 储存生产的鸡（总量为30）
    private Chicken[] chickens = new Chicken[10];
    // 指针
    private int i = 0;

    public Chicken(int id) {
        this.id = id;
    }

    public Chicken() { }

    public synchronized void push(Chicken chicken){
        // 如果储藏满了，则等待消费者消费
        while (chickens.length == i) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }

        chickens[i++] = chicken;
        notifyAll();
    }

    public synchronized Chicken pop() {
        // 如果储藏空了，则等待生产者生产
        while (i == 0) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }

        Chicken chicken = chickens[--i];
        notifyAll();
        return chicken;
    }
}
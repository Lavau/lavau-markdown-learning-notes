package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description: 生产者与消费者
 *
 * 高内聚低耦合：线程    操作      资源类
 * 判断/干活/唤醒
 * 防止虚假唤醒（判断条件改为while）
 *
 * @author: Leet
 * @date: 2020-11-23 19:36
 **/
public class ProdConsumerDemo4 {
    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

class AirCondition {
    private int number = 0;
    private Lock lock = new ReentrantLock();  // 可重入非空型递归锁
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try {
            // 1、判断
            while (number != 0) {
                condition.await();
            }
            // 2、干活
            number ++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 3、通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try {
            // 1、判断
            while (number == 0) {
                condition.await();
            }
            // 2、干活
            number --;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 3、通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
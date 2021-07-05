package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description: 定点通知
 *      使用 Lock 与 Condition 可以实现线程精确的唤醒
 *
 *      多线程之间按顺序调用，实现 A->B->C
 *      三个线程启动，要求如下：
 *          AA 打印 5 次，BB 打印 10 次，CC 打印 15 次
 *          接着
 *          AA 打印 5 次，BB 打印 10 次，CC 打印 15 次
 *          。。。（来 10 轮）
 *
 * @author: Leet
 * @date: 2020-11-23 20:16
 **/
public class ConditionDemo {
    public static void main(String[] args) {
        ShareDate shareDate = new ShareDate();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareDate.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareDate.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareDate.print15();
            }
        }, "C").start();
    }
}

class ShareDate {
    private int number = 1; // 标识位：A:1  B:2  C:3
    private Lock lock = new ReentrantLock();

    // 一把锁配多把备用钥匙
    // ProdConsumerDemo4.java 中是 一把锁配一把钥匙
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    /*public void print() {
        System.out.println("====");
        lock.lock();
        try {
            switch (number) {
                case 1:
                    condition2.await();
                    condition3.await();
                    break;
                case 2:
                    condition1.await();
                    condition3.await();
                    break;
                case 3:
                    condition1.await();
                    condition2.await();
                    break;
            }

            for (int i = 1; i <= number * 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number++;
            number %= 4;
            switch (number) {
                case 1:
                    condition1.signal();
                    break;
                case 2:
                    condition2.signal();
                    break;
                case 3:
                    condition3.signal();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }*/

    public void print5() {
        lock.lock();
        try {
            // 判断
            while (number != 1) {
                condition1.await();
            }
            // 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            number = 2; // 改标识位
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            // 判断
            while (number != 2) {
                condition2.await();
            }
            // 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            number = 3; // 改标识位
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            // 判断
            while (number != 3) {
                condition3.await();
            }
            // 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            number = 1; // 改标识位
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
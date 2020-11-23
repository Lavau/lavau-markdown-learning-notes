package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description: 三个售票员     卖    30张票
 *
 * 写企业级的多线程代码
 *
 * 1、在高内聚低耦合的前提下：线程    操作   资源类
 * （高内聚低耦合：我的理解
 *      对于一个类而言，就是把该类的功能性的接口，给暴露出去
 *      而接口的实现细节封装起来，在封装起来的代码块中，减少重复代码的使用，减少垃圾代码
 *  ）
 *
 * @author: Leet
 * @date: 2020-11-22 09:57
 **/
public class SaleTicketDemo1 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> { for (int i = 0; i < 40; i++) {ticket.sale();}},"A").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) {ticket.sale();}},"B").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) {ticket.sale();}},"C").start();
    }
}

/**
 * 资源类
 */
class Ticket {
    private int ticket = 30;
    private Lock lock = new ReentrantLock(); // 可重入锁

    public void sale() {
        lock.lock();
        try {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出了第 " + ticket-- +"\t张票");
            }
        } finally {
            lock.unlock();
        }
    }
}
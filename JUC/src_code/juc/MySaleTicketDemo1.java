//package juc;
//
///**
// * description: 三个售票员   卖出   30 张票
// *
// * @author: Leet
// * @date: 2020-11-22 09:03
// **/
//public class MySaleTicketDemo1 {
//    public static void main(String[] args) {
//        Ticket ticket = new Ticket();
//        new Thread(ticket, "B").start();
//        new Thread(ticket, "A").start();
//
//        new Thread(ticket, "C").start();
//    }
//}
//
//class Ticket implements Runnable{
//    private int ticket = 0;
//
//    @Override
//    public void run() {
//        synchronized (this) {
//            final int ticketAll = 129;
//            while (ticket <= ticketAll) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + " 买到了第 " + ++ticket + " 张票");
//            }
//        }
//    }
//}
//
//

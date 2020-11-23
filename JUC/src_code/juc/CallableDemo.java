package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * description: CallableTest
 *
 * @author: Leet
 * @date: 2020-11-23 21:03
 **/
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask  = new FutureTask(new MyThread());

        new Thread(futureTask, "A").start();

        System.out.println(futureTask.get());
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("******");
        return 1024;
    }
}
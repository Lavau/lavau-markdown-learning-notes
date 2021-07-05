package my.test;

import java.util.concurrent.*;

/**
 * description: Callable interface test
 *
 * @author: Leet
 * @date: 2020-11-23 20:47
 **/
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        A a = new A();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer> future1 = executorService.submit(a);
        Future<Integer> future2 = executorService.submit(a);
        Future<Integer> future3 = executorService.submit(a);

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());

        executorService.shutdown();
    }
}

class A implements Callable<Integer> {

    private int number = 1;

    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        return ++number;
    }
}

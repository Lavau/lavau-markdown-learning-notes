package test.thread;

import java.util.concurrent.*;

/**
 * description: 线程学习5：实现 Callable 接口
 * @author: Leet
 * @date: 2020-11-11 00:12
 **/
public class TestCallable5 implements Callable<Boolean> {

    private String work;

    public TestCallable5(String work) {
        this.work = work;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Boolean> result = es.submit(new TestCallable5("我在学习多线程---》"));
        Future<Boolean> result1 = es.submit(new TestCallable5("我在看代码---》"));
        result.get();
        result1.get();
        es.shutdown();
    }

    @Override
    public Boolean call() throws Exception {
//        Thread.sleep(2);
        for (int i = 0; i < 400; i++) {
            System.out.printf("%s%d\n", work, i);
        }
        return true;
    }
}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description: 线程池
 *
 * @author: Leet
 * @date: 2020-11-12 20:57
 **/
public class TestThreadPool implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);

        es.execute(new TestThreadPool());
        es.execute(new TestThreadPool());
        es.execute(new TestThreadPool());
        es.execute(new TestThreadPool());

        es.shutdown();
    }
}

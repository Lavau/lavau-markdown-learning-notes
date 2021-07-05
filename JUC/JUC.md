接口是可以被 `new` 的，体现在匿名内部类、`lambda`

[TOC]

# 1.  初始 `ReentrantLock` 类

* 位于 `JUC` 包

* 可重入锁

* 使用方法

  ```java
  Lock lock = ...;
  lock.lock();
  try{
      
  } finally {
      lock.unlock();
  }
  ```

  

## 1.1  举例

```java
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
```



# 2  `Lamdba experssion`

## 2.1  如何写

> 拷贝小括号()，写死右箭头->，落地大括号{}

## 2.2  `@FunctionalInterface`

这个注解标记只有一个方法（该接口可以 `default` 方法）的接口上，表明该接口是一个函数式的接口

如果该接口只有一个非 `default` 方法，那么在编译时，它会被默认地添加上该接口 

### 2.2.1  `default` 关键字

 + 用在接口的方法上
 + 为接口方法提供方法体
+ 实现该接口的类可以根据自己的需要判断是否覆写 default 方法

```java
/**
 * description: LambdaExpression
 *
 * lambda 如何写：
 *      拷贝小括号()，写死右箭头->，落地大括号{}
 *
 * @FunctionalInterface 这个注解标记只有一个方法（该接口可以 default 方法）的接口上，表明该接口是一个函数式的接口
 *
 * default 关键字：
 *      + 用在接口的方法上
 *      + 为接口方法提供方法体
 *      + 实现该接口的类可以根据自己的需要判断是否覆写 default 方法
 *
 *
 * @author: Leet
 * @date: 2020-11-22 10:37
 **/
public class LambdaExpressionDemo2 {
    public static void main(String[] args) {
        Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("***Hello, I'm foo.");
            }
        };
        foo.sayHello();

        Foo foo1 = () -> System.out.println("***Hello, I'm fool1");
        foo1.sayHello();

        Eoo eoo = (x, y) -> x + y;
        System.out.println(eoo.add(1, 2));

        System.out.println(new A().eat());
    }
}

@FunctionalInterface
interface Foo {
    void sayHello();
}

@FunctionalInterface
interface Eoo {
    int add(int x, int y);

    default int eat() {
        return 0;
    }
}

class A implements Eoo {

    @Override
    public int add(int x, int y) {
        return 0;
    }

    @Override
    public int eat() {
        return 22;
    }
}
```



# 3.  `CopyOnWriteArrayList` 类

`写时复制` （通过读 `CoppyOnWriteArrayList` 类的源码中的 `add()` 方法体现）

体现了读与写分离思想

## 3.1  `add()` 方法

```java
public E set(int index, E element) {
    final ReentrantLock lock = l.lock;
    lock.lock();
    try {
        rangeCheck(index);
        checkForComodification();
        E x = l.set(index+offset, element);
        expectedArray = l.getArray();
        return x;
    } finally {
        lock.unlock();
    }
}
```

> 可以对 `CopyOnWrite` 容器进行并发的读的时候，不需要加锁
>
> 因为当前容器捕获添加任何元素
>
> `CopyOnWrite` 容器是一种读写分离的思想；读和写分别是不同的容器

## 3.2  其他安全的容器类

### 3.2.1  安全的集合

`CopyOnWriteSet`

### 3.2.2  安全的map

`ConcurrentHashMap`

## 3.3  代码

```java
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * description: 不安全类
 *
 * 1. 故障现象
 *      java.util.ConcurrentModifiedException
 * 2. 原因
 *      共享资源 list 没有加锁，导致多线程争抢
 * 3. 解决方法
 *      3.1  new Vector<>()
 *      3.2  Collections.synchronizedList(new ArrayList())
 *      3.3  new CopyOnWriteArrayList<>()
 * 4. 优化建议
 *      与时俱进，使用更高版本的方法解决问题
 *      CopyOnWriteArrayList
 *      CopyOnWirteSet
 *      ConcurrentHashMap
 * 
 * @author: Leet
 * @date: 2020-11-22 17:33
 **/
public class NotSafeDemo3 {
    public static void main(String[] args) {
        mapsafe();
    }

    /**
     * ctrl + alt + M 抽取方法的快捷键
     */
    public static void mapsafe() {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);}
            ).start();
        }
    }

    /**
     * ctrl + alt + M 抽取方法的快捷键
     */
    public static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);}
            ).start();
        }
    }

    /**
     * ctrl + alt + M 抽取方法的快捷键
     */
    public static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);}
            ).start();
        }
    }
}
```



# 4.  多线程编程

1、在高内聚低耦合的前提下：线程    操作   资源类

（高内聚低耦合：我的理解
	对于一个类而言，就是把该类的功能性的接口，给暴露出去

​	而接口的实现细节封装起来，在封装起来的代码块中，减少重复代码的使用，减少垃圾代码）

2、编写线程协作的代码

```tex
判断/干活/通知
```

3、防止虚假唤醒

```tex
使用 while 判断
```

## 4.1  知识小总结

>多线程编程套路 +  while判断 + 新版写法

## 4.2  代码

> 关于 `lock、condition` 与 `synchorniched、wait、notify` 之间的对应关系请参看 JDK 1.8 中 `lock 与 condition` 的 API 

```java
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
```

# 5.  定点通知

> 使用 Lock 与 Condition 可以实现线程精确的唤醒

```java
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
```



# 6.  创建线程的方法

## 6.1  使用 `Thread class` 

## 6.2  使用 `Runnable interface`

## 6.3  使用 `Callable interface` 创建线程

 ```java
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
 ```

## 6.4  使用`线程池`

```java
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
```


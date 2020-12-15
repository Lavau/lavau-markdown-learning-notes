[TOC]

# 1.  自增变量——分析下面程序的输出结果

```java
package season.one;

/**
 * description season 1 1P_自增变量
 *
 * @author Leet
 * @date 2020-12-15 17:38
 **/
public class P1_SelfIncreasingVariable {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.printf("i: %d \nj: %d \nk: %d", i, j, k);
    }
}
```

# 2.  单例模式

## 2.1  小口诀

> 私有构造，静态存储

*解释：*

**private 修饰构造器，用 static （final）保存这个类的唯一实例对象**

## 2.2  两种实现方法

- 饿汉式（**直接**创建对象）
  - 在类加载时，直接生成类的单例
- 懒汉式（**延迟**创建对象）
  - 类加载后，通过某一方式获取类的单例
  - *特别注意**线程安全***

### 2.2.1  饿汉式

#### 2.2.1.1  直接实例化

```java
public class SingletonByIndirect {
    private SingletonByIndirect() {} 
    public static final SingletonByIndirect SINGLETON_BY_INDIRECT = new SingletonByIndirect();
}
```

#### 2.2.1.2  静态代码块

```java
public class SingletonByStaticCodeBlock {
    private String name;
    public static final SingletonByStaticCodeBlock SINGLETON_BY_STATIC_CODE_BLOCK;
    
    private SingletonByStaticCodeBlock(String name) {
        this.name = name;
    }
    
    static {
 		SINGLETON_BY_STATIC_CODE_BLOCK = new SingletonByStaticCodeBlock("leet");   
    }
}
```

### 2.2.2  懒汉式

#### 2.2.2.1  static 方法获取（单线程）

```java
public class SingletonByStaticFunction {
    private SingletonByStaticFunction() {}
    
    private static final SingletonByStaticFunction INSTANCE;
    
    public static SingletonByStaticFunction getInstance() {
        if (INSTANCE == null) {
            return new SingletonByStaticFunction();
        }
        return INSTANCE;
    }
}
```

#### 2.2.2.2  static 方法获取（多线程）

```java
package season.one;

import java.util.concurrent.*;

/**
 * description season 1 2P_单例模式
 *
 * @author Leet
 * @date 2020-12-15 18:11
 **/
class SingletonByStaticFunctionMoreThreads {
    private SingletonByStaticFunctionMoreThreads() {}

    private static SingletonByStaticFunctionMoreThreads INSTANCE;

    public static SingletonByStaticFunctionMoreThreads getInstance() {
        synchronized (SingletonByStaticFunctionMoreThreads.class) {
            if (INSTANCE == null) {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);

                    INSTANCE = new SingletonByStaticFunctionMoreThreads();

                } catch (InterruptedException e) {
                }
            }
        }
        return INSTANCE;
    }
}

class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<SingletonByStaticFunctionMoreThreads> threadsCallable = new Callable<SingletonByStaticFunctionMoreThreads>() {
            @Override
            public SingletonByStaticFunctionMoreThreads call() throws Exception {
                return SingletonByStaticFunctionMoreThreads.getInstance();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<SingletonByStaticFunctionMoreThreads> one = es.submit(threadsCallable);
        Future<SingletonByStaticFunctionMoreThreads> two = es.submit(threadsCallable);
        SingletonByStaticFunctionMoreThreads s1 = one.get();
        SingletonByStaticFunctionMoreThreads s2 = two.get();
        System.out.println(s1);
        System.out.println(s2);
        es.shutdown();
    }
}
```

#### 2.2.2.3  static 内部类实现（多线程安全）

```java
public class SingletonByStaticInnerClass {
    private SingletonByStaticInnerClass() {}
    static class InnerClass {
        static final SingletonByStaticInnerClass INSTANCE = new SingletonByStaticInnerClass();
    }
}
```



# 3.  类初始化与实例初始化


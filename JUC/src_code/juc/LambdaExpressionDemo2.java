package juc;

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
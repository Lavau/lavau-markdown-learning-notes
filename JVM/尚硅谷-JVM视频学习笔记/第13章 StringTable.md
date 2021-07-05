[TOC]

# 13.1  String 的基本特性

- <img src="E:\mdFiles\JVM\尚硅谷-JVM视频学习笔记\picture\chapter13-1.png" width="60%">

- JDK 8 之前，String 内部由 `final char[] value` 存储字符串数据，

  ​          之后，由 `final byte[] value` 存储

- String 具有 **不可变性**：

  1. 为字符串变量重新赋值，该变量指向常量池中一个新的内存空间
  2. 对现有字符串拼接，将产生一个新的字符串
  3. `replace()` 方法修改字符串，产生一个新串

- 通过字面量的形式为一个字符串赋值，此时的字符串值存在于字符串常量池中。

- <span style="color:red; font-weight:bold; font-size:18px">字符串常量池中不会存放相同内容的字符串</span>
  1. String 的 `String Pool` 是一个固定大小的 `Hashtable`。
  2. 如果放进 `String Pool` 中的 String 过多，会产出 Hash 冲突，导致链表过长，使 `String.intern() ` 方法被调用时性能下降
  3. JDK7 中默认值为60013，最小值为 1009
  4. `-XX:StringTableSize` 可设置 StringTable 的
- ``string.intern() `` 如果字符串常量池中无 string 指向的值，则创建；否则，不创建

# 13.2  String 的内存分配

常量池是 Java 系统级别的缓存。

String 类型的常量池主要的使用方法：

1. 用 `""` 直接为字符串变量赋值，值将直接存储到常量池中。
2. 调用字符串实例对象的 `intern()` 方法放入到常量池。

<img src="E:\mdFiles\JVM\尚硅谷-JVM视频学习笔记\picture\chapter13-2.png" width="80%">

**String Table 为何要调整？**

1. `permSize` 默认比较小
2. 永久代回收频率低

# 13.3  String 的基本操作



# 13.4  字符串拼接操作

- 常量（指字面量常量与 `final` 修饰的常量）与常量的拼接结果放在常量池。原理是前期编译器优化

  （<span style="color:red; font-weight:bold; font-size:18px">验证：</span> 写一个类，反编译得到的 class 文件或者直接看字节码）

- 只要一个是变量，结果就放在堆中。变量拼接原理是 `StringBuilder`

  （<span style="color:red; font-weight:bold; font-size:18px">验证：</span> 写一个类，直接看字节码）

- 如果拼接结果调用 `intern()` 方法：主动将结果放入到字符串常量池中。如果该字符已有，返回它的地址；否则，在字符串常量池中，创建后，在返回地址。

> 字符串的拼接效率：`StringBuilder` > `StringBuffer` > `string.concat()` > `+`

> 通过在构造 `StringBuilder` 时指定初始容量，进一步优化字符串的拼接效率。 

# 13.5  `intern()` 的使用

Java API 对 `intern()` 的解释：

>Returns a canonical representation for the string object.
>A pool of strings, initially empty, is maintained privately by the class String.
>When the intern method is invoked, if the pool already contains a string equal to this String object as determined by the equals(Object) method, then the string from the pool is returned. Otherwise, this String object is added to the pool and a reference to this String object is returned.
>It follows that for any two strings s and t, s.intern() == t.intern() is true if and only if s.equals(t) is true.
>All literal strings and string-valued constant expressions are interned. String literals are defined in section 3.10.5 of the The Java™ Language Specification.



使字符串变量 s 指向字符串常量池中数据的方法：

1. 直接字面量赋值

   ```java
   String str = "Java";
   ```

2. 调用 `intern()` 方法

   ```java
   String str = new String("avaj").intern();
   ```

## 13.5.1  以下代码创建了几个对象

```java
package com.atguigu.java2;

/**
 * description 126P——newString() 到底创建了几个对象
 *
 * @author Leet
 * @date 2020-12-28 15:28
 **/
public class StringNewTest {
    public static void main(String[] args) {
        String str = new String("ab");
    }
}
```

<span style="color:red; font-weight:bold; font-size:20px">2 个</span>

1. 一个是new 关键字在堆中创建的

2. 另一个是字符串常量池中的 （字节码指令：`ldc` ）

   <img src="E:\mdFiles\JVM\尚硅谷-JVM视频学习笔记\picture\chapter13-3.png" width="45%">

```java
package com.atguigu.java2;

/**
 * description 126P——newString() 到底创建了几个对象
 *
 * @author Leet
 * @date 2020-12-28 15:28
 **/
public class StringNewTest {
    public static void main(String[] args) {
//        String str = new String("ab");
        String str = new String("a") + new String("b");
    }
}
```

<span style="color:red; font-weight:bold; font-size:20px">6 个</span>

<img src="E:\mdFiles\JVM\尚硅谷-JVM视频学习笔记\picture\chapter13-4.png" width="60%">

## 13.5.2  回答下面的结果

```java
package com.atguigu.java2;

/**
 * description 127P——关于intern()的面试难题
 *
 * @author Leet
 * @date 2020-12-28 15:56
 **/
public class StringIntern {
    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);  //JDK6/7/8:false

        String s3 = new String("1") + new String("1");
        s3.intern();  // JDK6 与 JDK7/8 的处理方式不同（见图）
        String s4 = "11";
        System.out.println(s3 == s4);  //JDK6:false  JDK7/8:true
    }
}
```

<img src="E:\mdFiles\JVM\尚硅谷-JVM视频学习笔记\picture\chapter13-5.png" width="45%">

### 13.5.2.1  扩展

```java
public class StringInternPlus {
    public static void main(String[] args) {
        String s3 = new String("1") + new String("1");
        String s4 = "11";
        String s5 = s3.intern(); 
        System.out.println(s3 == s4); 
        System.out.println(s5 == s4); 
    }
}
```

false  true

# 13.6  String Table 的垃圾回收

```java
package com.atguigu.java3;

/**
 * description 132P——StringTable的垃圾回收测试
 *      -Xms10M -Xmx10M -XX:+PrintStringTableStatistics -XX:+PrintGCDetails
 *
 * @author Leet
 * @date 2020-12-28 16:34
 **/
public class StringGCTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            String.valueOf(i).intern();
        }
    }
}
```



# 13.7  G1 中的 String 去重操作




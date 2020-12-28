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



13.6  String Table 的垃圾回收



13.7  G1 中的 String 去重操作




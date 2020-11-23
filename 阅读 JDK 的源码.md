[TOC]

# 1.  准备 IDEA

使用集成开发环境 `IDEA` 阅读源码

[IDEA 下载地址](https://www.jetbrains.com/idea/download/#section=windows)

《图片1》

我使用的版本是 2020.2 Ultimate

> 推荐使用 Ultmate 版本，功能更完善
>
> 不过收费
>
> 如果你是学生党可以根据用学生邮箱申请免费使用（我就是这样）

**安装就省略了**



# 2.  配置 IDEA

## 2.1  重新配置 Debug

使用 IDEA 进入 Debug 时，正常情况下，是不会进入到 JDK 源码包中的

此时，就需要我们做些修改了

> 按照以下方式进入到修改界面后，按图上的标记修改即可

```mermaid
graph LR
   File --> Setting --> Build,Execution,Deployment --> Debuger --> Stepping 
```

《图片2》

## 2.2  写个小 Demo 试试

```java
import java.util.ArrayList;
import java.util.List;

/**
 * description: test
 *
 * @author: Leet
 * @date: 2020-11-20 08:52
 **/
public class Hello {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(3);  // 在此处的做左边加上断点
        list.add("Tom");
        list.add("Jack");
        list.add("King");
    }
}
```

```mermaid
graph LR
 在左边加断点 --> 进入Debug模式 --> 点击控制台上的下一步就可以进入JDK的源码了
```



《图片3》

# 3.  对 JDK 源码做修改

虽然现在能进入到 JDK 源码中，看到代码执行流程了

但是，还想对 JDK 源码做些修改，比如说加上包含自己的理解的注释，此时你发现，将无法添加注释（将显示 `read only`）。

那么接下来，就让看看如何修改源码吧。

1. `ctrl + alt + shift + S` 打开 `Project Structuer`

2. 找到拷贝这个文件到 `myJdkSrc` 下并解压（在项目下新建文件夹 myJdkSrc）

《4》

《5》

3. 替换原来的 `"src"` 文件

《6》

大功告成

《7》


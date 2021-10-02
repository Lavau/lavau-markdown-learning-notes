[TOC]

# 使用注解的好处

* 完整地描述不能用 Java 传达的信息
* 存储有关程序的额外的信息，供编译器测试和验证
* 可生成描述符文件或新的类的定义，减轻编写“样本”的负担
* 使代码更简洁易读，提供编译器类型检查



# 定义注解

定义注解时，用到的元注解：

* @Target

  定义注解用于何处（一个方法 METHOD 或一个域 FIELD .......）

* @Retention

  定义注解在哪一级别使用（源码级 SOURCE 或类文件 CLASS 或运行时 RUNTIME）

定义注解像是在定义接口。

不同的是注解中，一般会包含一些元素表示某些值，并且你可以为它指定默认值。

使用注解时，表现为名-值对的形式。

## 举个例子

你是一名项目经理。你想要在项目开发过程中实时把握进度。于是，你想到了使用注解。

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase{
    int id();
    String description() default "no description";
}
```

你使用的 ```UseCase``` 注解中 id 表示开发的进展到哪一步，description 表示对被标注的方法进行说明。

```java
/**
 * @program: annotation
 * @description: 学习注解的代码
 * @author: Leet
 * @create: 2020-10-11 21:32
 **/
public class PasswordUtils {
    
    @UseCase(id = 47, description = "Password must contain at least one numeric")
    public boolean validatePassword(String password){
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id = 48)
    public String encryptPassword(String password){
        return new StringBuffer(password).reverse().toString();
    }
}
```

## 元注解

Java 目前支持四种元注解：

<table>
    <thead>
        <tr>
        	<th></th>
            <th>说明</th>
            <th>参数</th>
        </tr>
    </thead>	
    <tbody>
    	<tr>
        	<td>@Target</td>
            <td>表示注解用于何处</td>
            <td>CONSTRUCTOR、FIELD、LOCAL_VARIABLE、METHOD、PACKAGE、PARAMTER、TYPE</td>
        </tr>
        <tr>
        	<td>@Retention</td>
            <td>表示注解在哪一级使用</td>
            <td>源码级 SOURCE 、类文件 CLASS 、运行时 RUNTIME</td>
        </tr>
        <tr>
        	<td>@Document</td>
            <td>将此注解包含 Javadoc 中</td>
            <td></td>
        </tr>
        <tr>
        	<td>@Inherited</td>
            <td>表示注解可继承</td>
            <td></td>
        </tr>
    </tbody>
</table>

Java 目前支持的三种标准注解：

<table>
    <td>@Override</td>
    <td>@Deprecated</td>
    <td>@SuppressWarnings</td>
</table>
## 注解元素

注解元素可用类型：

* 所有基本类型
* String
* Class
* enum
* Annotation
* 以上类型的数组

编译器对于注解中元素尤其很苛刻：编译时，元素都要有值。

那么这就要求，元素要么有默认值（null 不能作为使用），要么在使用注解时为元素提供值。

也就是说，每个注解的声明中，每个元素都存在，且都有相应的值。

# 补充

注解不支持（现行版）继承。
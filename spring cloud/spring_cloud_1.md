[TOC]

# 1.  准备

## 1.1  `spring boot` 与 `spring cloud ` 的关系

`spring boot` 版本命名以数字为准

`spring cloud` 版本命令以英语单词为准

但是二者要想结合使用的话，用严格的版本搭配限制。

[版本搭配查看（json格式）](https://start.spring.io/actuator/info)

## 1.2  阳哥的源码

[https://github.com/Lavau/atguigu_spirngcloud2020](https://github.com/Lavau/atguigu_spirngcloud2020)

一些基本配置自己可以在这里找到

## 1.3  知识补充

### 1.3.1  区分 `dependencyManagement`  与 `dependencies` 

* `dependencyManagement`

  1. 用在父工程，对子工程进行统一的定义
  2. 只负责定义，并不负责实现

  《下面的内容仅供参考》

  + Maven 使用 `dependencyManagement` 提供一种管理依赖版本号的方式

  + 通常，会在一个组织或项目的最顶层的父 `pom` 中看到 `dependencyManagement`

  + 使用 `pom.xml` 中的 `dependencyManagement` 让所有在子项目中引用一个依赖而不显示的列出版本号：

    > Maven 沿着父子层次向上走，直到找到一个拥有 `dependencyManagement` 元素的项目，然后它会使用这个 `dependencyManagement`  元素指定的版本号



# 2.  如何写微服务模块

> 约定 > 配置 > 编码

1. 建 `module` （建微服务模块）
2. 改 `pom ` （改写项目的 pom 文件）
3. 写 `yml` （填写配置文件）
4. 主启动 
5. 业务类 （编写业务类）
   1. 建表 SQL
   2. entity
   3. dao
   4. service
   5. controller



# 3.  `RestTemplate` 

`RestTemplate` 提供了多种便捷访问远程 HTTP 服务的方法，是一种简单便捷访问的访问 restful 服务模板类，是 Spring 提供的用于访问 Rest 服务的客户端模板工具集。

## 3.1  使用

`restTemplate` 访问 restful 接口非常简单。

`url、requestMap 、ResponseBean.class` （这三个参数）分别代表 Rest 请求地址、请求参数、HTTP 响应转换为

## 3.2  官网 api

[官网 api](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html)



# 4.  知识补充

## 4.1  `@RequestBody`

`@RequestBody` 主要用来接收前端传递给后端的 `json` 字符串中的数据的(请求体中的数据的)

[`@RequestBody` 详解](https://blog.csdn.net/justry_deng/article/details/80972817)



# 5.  Eureka 服务注册与发现

<img src="E:\mdFiles\spring cloud\picture\1.png" height=250>

## 5.1  基础知识

### 5.1.1  什么是服务治理

> 管理服务与服务之间的依赖关系，实现服务调用、均衡负载、容错等，实现服务发现与注册
>
> （传统的 rpc 远程框架中，服务与服务之间的依赖关系比较复杂，管理起来比较麻烦）
>
> *注：*
>
> *rpc：（Remote Procedure Call）远程过程调用，简单的理解是一个节点请求另一个节点提供的服务*

### 5.1.2  什么是服务注册

Eureka 采用 CS 架构

Eureka Server 作为服务注册功能的服务器，它是服务注册中心

系统中的其他微服务使用 Eureka 客户端连接到 Eureka Server，并维持心跳连接。维护人员通过 Eureka Server 监控各个微服务是否正常运行

### 5.1.3  Eureka 的两个组件

#### 5.1.3.1  Eureka Server

* 提供服务注册

  各个微服务节点通过配置启动后，在 Eureka Server 中注册；

  Eureka Server 的服务注册表存储所有可用服务节点的信息

#### 5.1.3.2  Eureka Client

* 通过服务中心进行访问

  一个 Java 客户端

  它应具备一个内置的、使用轮询负载算法的负载均衡器

  应用启动后，向 Eureka Server 发送心跳。Eureka Server 在多个心跳周期内没有接受到某个节点的心跳，Eureka Server 将它从服务注册表中除去


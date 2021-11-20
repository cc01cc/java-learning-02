# Spring 学习笔记

## 概述

- Spring 是一个生态
- Spring Framework 是 Spring 生态的基石
- Spring Boot 是基于 Spring Framework 构建的框架
- Spring Cloud 是 Spring Boot 的微服务应用，类似于解决方案

## 官方参考

- Spring Framework - doc&api : <https://spring.io/projects/spring-framework#learn>

## 默认文件结构

### Maven-Spring-Thymeleaf

```xml
src
 └─main
     ├─java
     │  └─ApplicationConfig.java
     └─resources
         └─static (js, css, img 等静态资源)
         └─templates (视图页面)
```

## 外部参考

### beans.xml 模板

> <https://docs.spring.io/spring-framework/docs/5.2.17.RELEASE/spring-framework-reference/core.html#beans-factory-metadata>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="...">  
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->

</beans>
```

#### 有参构造

> <https://docs.spring.io/spring-framework/docs/5.2.17.RELEASE/spring-framework-reference/core.html#beans-constructor-injection>

### 自动装配

> beans factory autowire: <https://docs.spring.io/spring-framework/docs/5.2.17.RELEASE/spring-framework-reference/core.html#beans-factory-autowire>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    // Spring looks for bean with the same name in context as the property that need to be autowired.
    <bean id="..." class="..." autowire="byName">  
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    // Spring looks for bean with the same type incontext as the property that need to be autowired.
    <bean id="..." class="..." autowire="byType">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->

</beans>
```

### 注解配置

> beans annotation config : <https://docs.spring.io/spring-framework/docs/5.2.17.RELEASE/spring-framework-reference/core.html#beans-annotation-config>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

- `@Autowired` : 通过 `byType` 实现
- `@Autowired` : 仅当 `value=False` 时，对象可以为 `null`
- `@Nullable` : 声明对象的值可以为 `null`
- `@Qualifier` : 配合 `Autowired` 使用，通过 `value="..."` 以指定唯一的 bean 对象注入
- `@Resource` : 默认通过 `byName` 实现，失败时通过 `byType` 实现
- `@Component` : 申明组件，说明该类被 Spring 管理

### classpath 与 classpath* 的区别

> 详解Spring项目中的classpath路径_Vermont_的博客-CSDN博客 : <https://blog.csdn.net/Vermont_/article/details/109118810>
> Spring配置中的"classpath:"与"classpath*:"的区别研究（转） - EasonJim - 博客园 : <https://www.cnblogs.com/EasonJim/p/6709314.html>

### @EnableTransactionManagement 的使用

> @EnableTransactionManagement的使用 - 那些年的代码 - 博客园 : <https://www.cnblogs.com/zhuyeshen/p/10907632.html>

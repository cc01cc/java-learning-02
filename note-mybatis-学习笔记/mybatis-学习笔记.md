# MyBatis 学习笔记

## MyBatis 与 JAVA

- 目录结构

```TXT
/my_application
  /bin
  /devlib
  /lib                <-- MyBatis *.jar 文件在这里。
  /src
    /org/myapp/
      /action
      /data           <-- MyBatis 配置文件在这里，包括映射器类、XML 配置、XML 映射文件。
        /mybatis-config.xml
        /BlogMapper.java
        /BlogMapper.xml
      /model
      /service
      /view
    /properties       <-- 在 XML 配置中出现的属性值在这里。
  /test
    /org/myapp/
      /action
      /data
      /model
      /service
      /view
    /properties
  /web
    /WEB-INF
      /web.xml
```

### SqlSession

- 实例和对象的区别
- 类的实例是对象，实例表示的是类和对象之间的关系

- SqlSession : 接口
- SqlSessionFactory : 对象，用于创建 SqlSession 的实例
- SqlSessionFactoryBuilder : 对象，用于创建 SqlSessionFactory (通过 XML, JAVA 配置, 注解)

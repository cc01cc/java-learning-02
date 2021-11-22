# Java-learning-02

## 介绍

Java 学习仓库；旧仓库过于臃肿，且结构稍有些混乱，故建新仓库

## Maven

### Maven 指定编译 JDK 版本

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.encoding>UTF-8</maven.compiler.encoding>
    <java.version>11</java.version>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
</properties>
```

### Maven 屏蔽 web.xml

```xml
<build>
<plugins>
    <plugin>
        <!-- https://maven.apache.org/plugins/maven-war-plugin/ -->
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.3.2</version>
        <configuration>
            <failOnMissingWebXml>false</failOnMissingWebXml>
        </configuration>
    </plugin>
</plugins>
</build>
```

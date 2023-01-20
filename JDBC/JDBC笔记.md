# # 1. 前言

---

## # 1.0 为什么要学习 JDBC 技术？

- **原因一：Java 和数据库必要纽带**

  <img src="https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112102325934.png" alt="image-20230112102325934" style="zoom: 50%;" /> 

- **原因二：数据库层框架底层原理**

  <img src="https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112102418479.png" alt="image-20230112102418479" style="zoom: 50%;" />

## # 1.1 课程需要哪些前置技术?

|      技术       |           版本            |   备注   |
| :-------------: | :-----------------------: | :------: |
|  IntelliJ IDEA  | 2022.3 (Ultimate Edition) | 最新版本 |
|       JDK       |            1.8            |          |
| MySQL-JDBC 驱动 |          8.0.27           | 8.0.25+  |
|      druid      |          1.1.21           |          |
|      MySQL      |          8.0.25           |          |

> ​	前置技术：

- **需要软件：**
  - **MySQL 软件安装（8+版本）**
  - **MySQL 可视化工具安装**
  - **IDEA 工具安装（推荐2022版本）**
- **SQL 语句：**
  - **掌握数据库连接命令**
  - **掌握基本的DDL,DQL,DML等命令**
  - **掌握数据库事务概念**
- **Java 基础语法：**
  - **多态机制**
  - **基本容器使用（集合和数组等）**
  - **泛型**
  - **反射等技术**

## # 1.2 课程学习路线设计

> 主要围绕着：学	悟	行

- **==阶段一：JDBC 概念理解==**
  - **深入理解 JDBC 本质**
  - **掌握 JDBC 版本变迁**
  - **掌握 JDBC 使用路线**
- **==阶段二：核心 API 使用==**
  - **核心 API 使用**
  - **核心 API 扩展使用强化**
- **==阶段三：优化提升==**
  - **国货之光 druid 连接池**
  - **工具类优化封装**
  - **Dao 层提取优化**
- **==阶段四：实战练习==**
  - **CMS 项目介绍和分层**
  - **CMS 项目实战练习**

# # 2. 全新 JDBC 技术概述

---

## # 2.1 JDBC 技术概念和理解

### # 2.1.1 JDBC技术理解

​	**JDBC：Java Database Connectivity | Java 数据库连接！**

​	**通俗点说，在 Java 代码中，使用 ==JDBC 提供的方法==，可以==发送==字符串类型的 ==SQL== 语句到==数据库管理软件==（MySQL,Oracle  等），并且获取==语句执行结果==！进而实现数据库数据 CRUD 操作的技术！**

<img src="https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112101432055.png" alt="image-20230112101432055" style="zoom: 50%;" />

### # 2.1.2 JDBC技术演示

<img src="https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112101645762.png" alt="image-20230112101645762" style="zoom: 50%;" />

### # 2.1.3 JDBC 本质理解

<img src="https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112101844626.png" alt="image-20230112101844626" style="zoom: 50%;" />

### # 2.1.4 JDBC 概念总结

- **JDBC 是（==Java Database Connectivity==）单词的缩写，翻译为 Java 数据库连接。**
- ==**JDBC 是 Java 程序连接数据库的技术统称。**==
- ==**JDBC 是 Java 语言的规范（接口）和各个数据库产商的实现驱动（jar）组成。**==
- ==**JDBC 是一种典型的面向接口编程。**==
- **JDBC 优势**
  - **只需要学习 JDBC 规范接口的方法，即可操作==所有的数据库软件。==**
  - **项目中期切换数据库软件，只需要更换对应的数据库驱动 jar 包，不需要更换代码。**

## # 2.2 JDBC 核心 API 和使用路线

### # 2.2.1 JDBC 技术组成

- **==JDK 下 JDBC 规范接口，存储在 java.sql 和 javax.sql 包中的 API。==**
  - **为了项目代码的可移植性，可维护性，SUN 公司从最初就制定了 Java 程序连接各种数据库的统一接口规范。这样的话，不管是连接哪一种 DBMS 软件，Java 代码可以保持一致性。**
- **==各个数据库厂商提供的驱动 jar 包==**
  - **因为各个数据库厂商的 DBMS 软件各有不同，那么内部如何通过 sql 实现增、删、改、查等管理数据，只有这个数据库厂商自己更清楚，因此把接口规范的实现交给各个数据库厂商自己实现。**
- **==jar 包是什么？==**
  - **Java 程序打成的一种压缩包格式，你可以将这些 jar 包引入到你的项目中，然后你可以使用这个 Java 程序中类和方法以及属性了！**

### # 2.2.2 涉及具体核心类和接口

- **==DriverManager==**
  - **将第三方数据库厂商的实现驱动 jar 注册到程序中。**
  - **可以根据数据库连接信息获取 connection 。**
- **==Connection==**
  - **和数据库建立的连接，在连接对象上，可以多次执行数据库 CRUD 动作。**
  - **可以获取 statement 和 preparedstatement,callablestatement 对象。**
- **==Statement | Preparedstatement | Callablestatement==**
  - **具体发送 SQL 语句到数据库管理软件的对象。**
  - **不同发送方式稍有不同！==preparedstatement== 使用为重点!**
- **==Result==**
  - **==面向对象思维的产物==（抽象成数据库的查询结果表）。**
  - **存储 DQL 查询数据库结果的对象。**
  - **需要我们进行解析，获取具体的数据库数据。**

### # 2.2.3 JDBC API 使用路线 

- ==**静态 SQL 路线（没有动态值语句）==**
  - **DriverManager**
  - **Connection**
  - **Statement**
  - **Result**
- **==预编译 SQL 路线（有动态值语句）==**
  - **DriverManager**
  - **Connection**
  - **PreparedStatement**
  - **Result**
- **==执行标准存储过 SQL 路线==**
  - **DriverManager**
  - **Connection**
  - **CallableStatement**
  - **Result**

## # 2.3 为什么选择全新 8+ 版本 mysql-jdbc 驱动？

​		**相关链接：**https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-whats-new.html

- ==**支持 8.0 + 版本 MySQL 数据管理软件**==

  - **MySQL 软件知名版本迭代时间**

    | 版本号       | 迭代时间     | 大小   |
    | ------------ | ------------ | ------ |
    | mysql-8.0.25 | 4月 30, 2021 | 435.7M |
    | mysql-5.7.25 | 1月 10, 2019 | 387.7M |
    | mysql-5.5.30 | 9月 19, 2012 | 201.5M |

    

  - **MySQL 8.x 版本数据库性能提升介绍**

    - **性能提升级。官方表示 MySQL 8.0 的速度要比 MySQL 5.7 快 2 倍。**
    - **MySQL 8.0 在读/写工作负载、IO 密集型工作负载、以及高竞争工作负载时相比MySQL5.7有更好的性能。**

    ![image-20230111220112570](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230111220112570.png)

- ==**支持Java JDBC 规范 4.2+ 版本新特性**==

  - **Java JDBC 规范驱动和更新时间**

    **==Year    JDBC Version   JSR Specification   JDK Implementation==**

    **2017    JDBC 4.3           JSR 221                           Java SE 9**

    **==2014    JDBC 4.2           JSR 221                           Java SE 8==**

    **2011    JDBC 4.1           JSR 221                           Java SE 7**

    **2006    JDBC 4.0           JSR 221                           Java SE 6**

    **2001    JDBC 3.0           JSR 54                              JDK 1.4**

    **1999    JDBC 2.1                                                         JDK 1.2**

    **1997    JDBC 1.2                                                         JDK 1.1**

  - **JDBC 规范版本更新内容（了解）**

    **==JDBC 4.3== 中引入的主要新功能包括：**
    **添加了对分片的支持**
    **添加了 java.sql.连接生成器接口**
    **添加了 java.sql.ShardigKey 接口**
    **添加了 java.sql.分片密钥生成器接口**
    **添加了.sql.==XA== 连接生成器接口**
    **添加了 javax.sql.池连接生成器接口**

    

    **==JDBC 4.2== 中引入的主要新功能包括：**
    **添加了对引用光标的支持**
    **添加了 java.sql.驱动程序操作接口**
    **添加了.sql.SQLType 接口**
    **添加 java.sql.JDBCType 枚举**
    **一些 ==JDBC== 接口更改**

    

    **==JDBC 4.1== 中引入的主要新功能包括：**
    **添加了对“使用资源试用”语句的支持**
    **增强的日期值和时间戳值**
    **从 Java 对象到 ==JDBC== 类型的其他映射**
    **一些 ==JDBC== 接口更改**

    

    **==JDBC 4.0== 中引入的主要新功能包括：**
    **自动加载爪哇.sql.驱动程序**
    **数据类型支持**
    **国家字符集转换支持**
    **支持**

    

    ==由于 JDBC 4.3 API 是向后兼容的，因此将 Java SE 9 或更高版本与 JDBC 4.2、4.1、4.0 
    或 3.0 驱动程序一起使用没有问题，只要您不使用 JDBC 4.3 API 中引入的新方法或类。==

  

- **==支持 JDK1.8 版本语法变更特性==**

  ​	**Connector/J 8.0是专门为在 Java 8 平台上运行而创建的。**

  ​	**众所周知，Java8 与早期的 Java 版本高度兼容，但还是存在少量不兼容性，所以,驱动技术版本,尽量选择支持 JDK 8.0+!**

- **==支持全新的驱动 api，增加自动时区选择和默认 utf-8 编码格式等配置==**

# # 3. 全新 JDBC 核心 API

---

## # 3.1 引入 mysql-jdbc 驱动 jar

### # 3.1.1 驱动 jar 版本选择

> ​	**我们选择版本 8.0.27 版本**
>

| MySQL 版本  | 推荐驱动版本 |                           备注                           |
| :---------: | :----------: | :------------------------------------------------------: |
| MySQL 5.5.x |    5.0.x     |                  com.mysql.jdbc.Driver                   |
| MySQL 5.7.x |     5.1x     |                 com.mybatis.jdbc.Driver                  |
|  MySQL 8.x  |     8.0x     | 建议：8.0.25+ 省略时区设置<br />com.mysql.cj.jdbc.Driver |

### # 3.1.2 Java工程导入依赖

- **新建一个名为 linchenxi-jdbc 的工程**

<img src="https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112115522944.png" alt="image-20230112115522944" style="zoom: 52%;" />

- **项目创建 lib 文件夹**

![image-20230112120029887](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112120029887.png)

- **导入驱动依赖 jar 包**

![image-20230112120657521](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112120657521.png)

- **jar 包右键-添加为项目依赖**

![image-20230112121421335](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112121421335.png)

![image-20230112121519876](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112121519876.png)

## # 3.2 JDBC 基本使用步骤分析（6步）

- ==**注册驱动**==
- ==**获取连接**==
- ==**创建发送 SQL 语句对象**==
- ==**发送 SQL 语句，并获取返回结果**==
- ==**结果集解析**==
- ==**资源关闭**==

<img src="https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112121721516.png" alt="image-20230112121721516" style="zoom:67%;" />

## # 3.3 基于 statement 演示查询

### # 3.3.1 准备数据库数据

```mysql
CREATE DATABASE atguigu;

USE atguigu;

CREATE TABLE t_user(
   id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键',
   account VARCHAR(20) NOT NULL UNIQUE COMMENT '账号',
   PASSWORD VARCHAR(64) NOT NULL COMMENT '密码',
   nickname VARCHAR(20) NOT NULL COMMENT '昵称');
   
INSERT INTO t_user(account,PASSWORD,nickname) VALUES
  ('root','123456','经理'),('admin','666666','管理员');
```

### # 3.3.2 查询目标

> ​	查询全部用户信息，进行控制台输出

![image-20230112123742592](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112123742592.png)

### # 3.3.3 基于 statement 实现查询（演示步骤）

```java
package com.atguigu.api.statement;
import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * @author 林晨曦
 * @description 使用 statement 查询 t_user 表下，全部的数据
 * @date 2023/1/12 13:12
 */
public class StatementQueryPart {

    /**
     * TODO:
     *      DriverManager
     *      Connection
     *      Statement
     *      ResultSet
     * @param args
     */

    public static void main(String[] args) throws SQLException {

        // 1.注册驱动
       /**
        * @param args:
        * @return void
        * TODO:
        *   注册驱动
        *   依赖：驱动版本 8+ com.mysql.cj.jdbc.Driver
        *        驱动版本 5+ com.mysql.jdbc.Driver
        */
        DriverManager.registerDriver(new Driver());
        // 2.获取连接
        /**
         * @param args:
         * @return void
         * TODO:
         *   Java 程序和数据库创建连接！
         *   Java 程序连接数据库，肯定是调用某个方法，方法也需要填入连接数据库的基本信息：
         *          数据库ip地址 127.0.0.1
         *          数据库端口号：3306
         *          账号：root
         *          密码：123456
         *          连接数据库的名称：atguigu
         *   小海豚
         *   Navicat
         *
         */

        /**
         * @param1 url
         *         jdbc:数据库产商名://ip地址:port/数据库名
         *         jdbc:mysql://127.0.0.1:3306/atguigu
         * @param2 username 数据库软件的账号  root
         * @param3 password 数据库软件的密码  123456
         * @return void
         * TODO
         */
        // java.sql 接口 = 实现类
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "123456");
        // 3.创建statement
        Statement statement = connection.createStatement();
        // 4.发送 SQL 语句，并且获取返回结果
        String sql = "select  * from t_user;";
        ResultSet resultSet = statement.executeQuery(sql);
        // 5.进行结果集解析
        // 先看看有没有下一行数据，有，你就可以获取
        while (resultSet.next()){

            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("PASSWORD");
            String nickname = resultSet.getString("nickname");

            System.out.println(id + "--" + account + "--" + password + "--" + nickname);
        }
        // 6.关闭资源 先用的后关闭
        resultSet.close();
        statement.close();
        connection.close();

    }
}
```

## # 3.4 基于 statement 方式问题

### # 3.4.1 本案例目标

- **明确 JDBC 流程和详细讲解使用（注册驱动，获取连接，发送语句，结果解析）**
- **发送问题，引出 preparedstatement**

### # 3.4.2 准备数据库数据

> ​	上个案例相同的数据库

```mysql
CREATE DATABASE atguigu;

USE atguigu;

CREATE TABLE t_user(
   id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键',
   account VARCHAR(20) NOT NULL UNIQUE COMMENT '账号',
   PASSWORD VARCHAR(64) NOT NULL COMMENT '密码',
   nickname VARCHAR(20) NOT NULL COMMENT '昵称');
   
INSERT INTO t_user(account,PASSWORD,nickname) VALUES
  ('root','123456','经理'),('admin','666666','管理员');
```

### # 3.4.3 演示目标

​	**模拟登录，控制台输入账号和密码，判断是否登录成功！**

<img src="https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112163451440.png" alt="image-20230112163451440" style="zoom:80%;" />

### # 3.4.4 基于 statement 实现模拟登录

```java
package com.atguigu.api.statement;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.statement
 * @className: StatementUserLoginPart
 * @author: 林晨曦
 * @description: 模拟用户登录
 * @date: 2023/1/12 13:38
 * @version: 1.0
 *
 * TODO：
 *   1. 明确 JDBC 的使用流程 和 详细讲解内部设计 api 方法
 *   2. 发现问题，引出 preparedStatement
 *
 * TODO：
 *   输入账号密码
 *   进行数据库信息查询（t_user）
 *   反馈登录成功还是登录失败
 *
 * TODO：
 *   1.键盘输入事件，收集账号和密码信息
 *   2.注册驱动
 *   3.获取连接
 *   4.创建statement
 *   5.发送查询SQL语句，并获取返回结果
 *   6.结果判断，显示登录成功还是失败
 *   7.关闭资源
 */


public class StatementUserLoginPart {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 1. 获取用户输入信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号:");
        String account = scanner.nextLine();
        System.out.println("请输入密码:");
        String password = scanner.nextLine();

        // 2. 注册驱动
        /**
         * @param args:
         * @return void
         * TODO:
         *      DriverManager.registerDriver(new com.mysql.jdbc.Driver())
         *      注意：
         *          8+ com.mysql.cj.jdbc.Driver
         *          5+ com.mysql.jdbc.Driver
         *      问题：注册两次驱动
         *          1. DriverManager.registerDriver() 方法本身会注册一次！
         *          2. Driver.static{DriverManager.registerDriver() } 静态代码块，也会注册一次
         *      解决：只想注册一次驱动
         *          只触发静态代码块即可！Driver
         *      触发静态代码块：
         *          类加载机制：类加载的时候，会触发静态代码块！
         *                      加载 [class 文件 -> jvm 虚拟机的 class 对象 ]
         *                      连接 [验证（检查文件类型） -> 准备（静态变量默认值）-> 解析（触发静态代码块）]
         *                      初始化（静态属性赋真实值）
         *      触发类加载：
         *          1. new 关键字
         *          2. 调用静态方法
         *          3. 调用静态属性
         *          4. 接口 1.8 default默认实现
         *          5. 反射
         *          6. 子类触发父类
         *          7. 程序的入口 main
         */
        // 方案1
        // DriverManager.registerDriver(new Driver());

        // 方案2 注册驱动，固定的写法！ MySQL - MySQL Driver || 切换了数据库 oracle driver | 还需要改代码
        // new Driver()

        // 触发类加载，触发静态代码块的调用
        // 字符串 -> 提取到外部配置文件 -> 可以在不改变代码的情况下，完成数据库驱动的切换！
        // 反射 字符串的Driver全限定符 可以引导外部的配置文件 -> xx.properties -> oracle -> 配置文件修改
        Class.forName("com.mysql.jdbc.Driver");


        /**
         * @param args:
         * @return void
         *
         * getConnection(1,2,3) 方法，是一个重载方法！
         * 允许开发者，用不同的形式传入数据库连接的核心参数！
         *
         * 核心属性：
         *      1.数据库软件所在的主机的ip地址：localhost | 127.0.0.1
         *      2.数据库软件所在的主机的端口号：3306
         *      3.连接的具体atguigu
         *      4.连接的账号 root
         *      5.连接的密码 123456
         *      6.可选的信息 没有
         *
         *  三个参数：
         *  String url   数据库软件所在的信息，连接的具体库，以及其他可选信息！
         *                 语法：jdbc:数据库管理软件名称[mysql | oracle]://ip地址| 主机名:port 端口号/数据库名?key=value
         *	如果有多个，可以用 &连接
         *                 &key=value 可选信息！
         *
         *                具体：jdbc:mysql://127.0.0.1:3306/atguigu
         *                     jdbc:mysql://localhost:3306/atguigu
         *                本机的省略写法：如果你的数据库软件安装到本机，你可以进行一些省略
         *                     jdbc:mysql://127.0.0.1:3306/atguigu = jdbc:mysql:///atguigu
         *                     省略了[本机地址]和[3306默认端口号]！
         *                     强调：必须是本机，并且端口号是3306可省略，使用///
         *  String user  数据库的账号  root
         *  String password  数据库的密码 123456
         *
         *
         *  二个参数：
         *  String url：此url和三个参数的url的作用一样！数据库ip，端口号，具体的数据库和可选信息
         *  Properties info：存储账号和密码
         *                  Properties 类似于 Map 只不过 key = value 都是字符串形式的！
         *                  key  user：账号信息
         *                  key password：密码信息
         *
         *   一个参数：
         *   String url ： 数据库ip，端口号，具体的数据库  可选信息（账号密码）
         *                 jdbc:数据库软件名://ip:port/数据库?key=value&key=value&key=value
         *
         *                 jdbc:mysql://localhost:3306/atguigu?user=root&password=123456
         *
         *                 携带固定的参数名 user password 传递账号和密码信息！[规定]
         *  url的路径属性 可选信息
         *      url?user=账号&password=密码
         *
         *      8.0.25以后，自动识别时区！ serverTimezone=Asia/Shanghai 不用添加！ 8.0.25之前版本，下面一句话还是要加的！
         *      8版本以后，默认使用的就是utf-8格式，useUnicode=true&characterEncoding=utf8&useSSL=true 都可以省略了！
         *
         *
         *      serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=true
         */
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123456");

        Properties info = new Properties();
        info.put("user","root");
        info.put("password","123456");

        Connection connection1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", info);

        Connection connection2 = DriverManager.getConnection("jdbc:mysql:///atguigu?user=root&password=123456");


        // 3.创建发送 SQL语句的 statement 对象
        //statement 可以发送 SQL 语句到数据库，并且获取返回结果！（小汽车）
        Statement statement = connection.createStatement();

        // 4.发送 SQL 语句（1.编写 SQL 语句 2.发送 SQL 语句）

        String sql = "select * from t_user where account ='"+ account +"' AND PASSWORD = '"+ password +"';";


        /**
         * @param sql  非DQL
         * @return int
         *          情况1：DML 返回影响的行数，例如：删除了三条数据 return 3;插入了两条 return 2; 修改了0条 return 0;
         *          情况2：非DML return 0;
         * int row = executeUpdate(sql);
         *
         * SQL分类：DDL（容器创建，修改，删除）  DML（插入，修改，删除）  DQL（查询） DCL（权限控制） TPL（事务控制语言）
         *
         *   参数：sql DQl
         *   返回：resultSet 结果封装对象
         *   ResultSet resultSet = statement.executeQuery(sql);
         *
         */

        // int i = statement.executeUpdate(sql);
        ResultSet resultSet = statement.executeQuery(sql);

        // 5. 查询结果集解析 resultSet
        /**
         * @param args:
         * @return void
         *
         *
         * Java是一种面向对象的思维，将查询结果封装成了 resultSet对象，我们应该理解，内部一定也是有行和有列！和小海豚的数据是一样的
         *
         * resultSet -> 逐行获取数据，行 -> 行的列的数据
         *
         *
         * A ResultSet object maintains a cursor pointing to its current row of data.
         * Initially the cursor is positioned before the first row.
         * The next method moves the cursor to the next row, and because it returns false when there are no more
         * rows in the ResultSet object, it can be used in a while loop to iterate through the result set.
         *
         *
         *  想要进行数据解析，我们需要进行两件事情：1.移动游标指定获取数据行  2.获取指定数据行的列数据即可
         *
         *
         *  1.游标移动问题
         *  resultSet 内部包含一个游标，指定当前行数据！
         *  默认游标指定的是第一行数据之前！
         *  我们可以调用next方法向后移动一行游标！
         *  如果我们有很多行数据，我们可以使用while(next){获取每一行的数据}
         *
         *  boolean = next() true：有更多行数据，并且向下移动一行
         *                   false: 没有更多行数据，不一定！
         *
         *  TODO：移动光标的方法有很多，只需要记next即可，配合while循环获取全部数据！
         *
         *  2. 获取列的数据问题（获取光标指定的行的列的数据）
         *
         *      resultSet.get类型(String columnLabel | int columnIndex );
         *       columnLabel:列名 如果有别名 写别名  select * | (id,account,password,nickname)
         *                                         select id as aid,account as  ac from
         *       columnIndex:列的下角标获取 从左向右 从1 开始
         */

        // while(resultSet.next()){
        // // 指定当前行
        //     int id = resultSet.getInt(1);
        //     String account1 = resultSet.getString("account");
        //     String password1 = resultSet.getString(3);
        //     String nickname = resultSet.getString("nickname");
        //
        //     System.out.println( id + "---" + account1 + "---" + password1 + "---" + nickname);
        //
        // }

        // 移动一次光标，只要有数据，就代表登录成功！
        if(resultSet.next()){

            System.out.println("登录成功！");
        }else{

            System.out.println("登录失败！");
        }

        // 6.关闭资源
        resultSet.close();
        statement.close();
        connection.close();

    }

}
```

### # 3.4.5 存在问题

- **SQL语句需要字符串拼接，比较麻烦**

- **只能拼接字符串类型，其他的数据库类型无法处理**

- ==**可能发生注入攻击**==

  - **动态值充当了 SQL 语句结构，影响了原有的查询结果！**

  <img src="https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112164546069.png" alt="image-20230112164546069" style="zoom:80%;" />

## ==# 3.5 基于 preparedStatement 方式优化==

> ​	==利用 preparedStatement 解决上述案例注入攻击和 SQL 语句拼接问题！==（重点掌握）

<img src="https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112172817302.png" style="zoom:80%;" />

```java
package com.atguigu.api.preparedstatement;

import java.sql.*;
import java.util.Scanner;

/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.preparedstatement
 * @className: PSUserLoginPart
 * @author: 林晨曦
 * @description: 使用预编译statement完成用户登录
 * @date: 2023/1/12 16:49
 * @version: 1.0
 *
 * TODO：防止注入攻击  |  演示 ps的使用流程
 */

public class PSUserLoginPart {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1. 收集用户信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号:");
        String account = scanner.nextLine();
        System.out.println("请输入密码:");
        String password = scanner.nextLine();

        // 2. ps的数据库流程
        // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?user=root&password=123456");

        /**
         * statement
         *     1.创建statement
         *     2.拼接 SQL 语句
         *     3.发送 SQL 语句，并且获取返回结果
         *
         * preparedStatement
         *  1.编写 SQL 语句结果 不包含动态值部分的语句，动态值部分使用占位符 ？替代 注意：？只能替代动态值
         *  2.创建PreparedStatement，并且传入动态值
         *  3.动态值  占位符 赋值 ？ 单独赋值即可
         *  4.发送 SQL 语句即可，并获取返回结果
         */

            // 3. 编写 SQL 语句结果
        String sql = "select  * from t_user where account = ? and password = ? ;";

            // 4. 创建预编译statement 并且设置 SQL 语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 5. 单独的占位符进行赋值
            /**
             * @param args:
             * @return void
             * 参数1：index 占位符的位置  从左向右数 从1开始 账号 ？ 1
             * 参数2：object 占位符的值 可以设置任何类型的数据，避免了我们拼接和类型更加丰富！
             */


        preparedStatement.setObject(1,account);
        preparedStatement.setObject(2,password);

        // 6. 发送 SQL 语句，并获取返回结果！
        // statement.executeUpdate | executeQuery(String sql);
        //prepareStatement.executeUpdate | executeQuery(); TODO:因为它已经知道语句，知道语句动态值！
        ResultSet resultSet = preparedStatement.executeQuery();

        // 7. 结果集分析
        if(resultSet.next()){
            System.out.println("登录成功！");
        }else {

            System.out.println("登录失败！");
        }

        // 8.关闭资源
        resultSet.close();
        preparedStatement.close();
        connection.close();


    }

}
```

<img src="https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112173137223.png" alt="image-20230112173137223" style="zoom:80%;" />

## ==# 3.6 基于 preparedStatement 演示 curd==

### # 3.6.1 数据库数据插入

```java
 @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
    /**
     * @param :
     * @return void
     * account test
     * password test
     * nickname 林晨曦
     */
    // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
    // 2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu","root","123456");
    // 3. 编写 SQL 语句结果，动态值的部分使用？代替
        String sql = "insert into t_user(account,password,nickname) values(?,?,?)";
    // 4. 创建preparedStatement，并且传入 SQL 语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
    // 5. 占位符赋值
        preparedStatement.setObject(1,"test");
        preparedStatement.setObject(2,"test");
        preparedStatement.setObject(3,"林晨曦");
    // 6. 发送 SQL 语句
        int rows = preparedStatement.executeUpdate();
    // 7. 输出结果
        if(rows > 0){
            System.out.println("数据插入成功!");
        }else{
            System.out.println("数据插入失败！");
        }
    // 8. 关闭资源
        preparedStatement.close();
        connection.close();

    }
```

![image-20230112210121729](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112210121729.png)

### # 3.6.2 数据库数据修改

```java
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {

        /**
         * @param :
         * @return void
         * 修改 id =3 的用户 nickname = 吴晨曦
         */

        // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu","root","123456");
        // 3. 编写 SQL 语句结果，动态值的部分使用？代替
        String sql = "update  t_user set nickname = ?  where id = ?";
        // 4. 创建preparedStatement，并且传入 SQL 语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        preparedStatement.setObject(1,"吴晨曦");
        preparedStatement.setObject(2,3);
        // 6. 发送 SQL 语句
        int rows = preparedStatement.executeUpdate();
        // 7. 输出结果
        if(rows > 0){
            System.out.println("修改成功!");
        }else{
            System.out.println("修改失败！");
        }
        // 8. 关闭资源
        preparedStatement.close();
        connection.close();

    }
```

![image-20230112210218475](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112210218475.png)

### # 3.6.3 数据库数据删除

```java
 @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
    /**
     * @param :
     * @return void
     * 删除 id =3 的用户数据
     */

        // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu","root","123456");
        // 3. 编写 SQL 语句结果，动态值的部分使用？代替
        String sql = "delete  from  t_user   where id = ?";
        // 4. 创建preparedStatement，并且传入 SQL 语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        preparedStatement.setObject(1,3);
        // 6. 发送 SQL 语句
        int rows = preparedStatement.executeUpdate();
        // 7. 输出结果
        if(rows > 0){
            System.out.println("删除成功!");
        }else{
            System.out.println("删除失败！");
        }
        // 8. 关闭资源
        preparedStatement.close();
        connection.close();
    }
```

![image-20230112210847494](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230112210847494.png)

### ==#3.6.4 数据库数据查询==

```java
 @Test
    public void testSelect() throws ClassNotFoundException, SQLException {
    /**
     * @param :
     * @return void
     * 查询所有用户数据，并且封装到一个List<Map> list集合中！
     *
     * 解释：
     *      行  id  account password nickname
     *      行  id  account password nickname
     *
     *  数据库 -> resultSet -> java -> 一行 -map(key=列名，value=列的内容) -> List<Map> list
     *
     *
     *  实现思路：
     *      遍历行数据，一行对应一个map！获取一行的列名和对应的列的属性，装配即可！
     *      将map装到一个集合就可以了！
     *
     *  难点：
     *      如何获取列的名称？
     */


        // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu","root","123456");
        // 3. 编写 SQL 语句结果，动态值的部分使用？代替
        String sql = "select id,account,password,nickname   from t_user";
        // 4. 创建preparedStatement，并且传入 SQL 语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        // 省略占位符赋值
        // 6. 发送 SQL 语句
        ResultSet resultSet = preparedStatement.executeQuery();
        // 7. 结果集解析
        /**
         * @param :
         * @return void
         * TODO:回顾
         *  resultSet:有行有列！获取数据的时候，一行一行数据！
         *              内部有一个游标，默认指向数据的第一行之前！
         *              我们可以利用next()方法移动游标！指向数据行！
         *
         *              获取行中的列的数据
         */

        List<Map> list = new ArrayList<>();
        // 获取列的信息对象
        // TODO: metaData 装的当前结果集列的信息对象！（他可以获取列的名称根据下角标，可以获取列的数量）
        ResultSetMetaData metaData = resultSet.getMetaData();


        // 有了它以后，我们可以水平遍历列！
        int columnCount = metaData.getColumnCount();


        while(resultSet.next()){

            Map map = new HashMap();
        //     一行数据对应一个map
        //     纯手动取值
        //     map.put("id",resultSet.getInt("id"));
        //     map.put("account",resultSet.getString("account"));
        //     map.put("password",resultSet.getString("password"));
        //     map.put("nickname",resultSet.getString("nickname"));


        //     自动遍历列
            // 自动遍历 注意，要从1开始，并且小于等于总列数！
            for (int i =1;i <= columnCount;i++){
                // 获取指定列下角标的值！ resultSet
                Object value = resultSet.getObject(i);

                // 获取指定列下角标的列的名称! ResultSetMetaData

                // getColumnLabel：会获取别名，如果没有写别名才是列的名称  不要使用 getColumnName:只会获取列的名称
                String columnLabel = metaData.getColumnLabel(i);
                map.put(columnLabel,value);


                // for循环中，也可以这样写
                // map.put(metaData.getColumnLabel(i),resultSet.getObject(i));
            }

            //     一行数据的所有列全部存到了map中！
            //     将map存储到集合中即可
            list.add(map);

        }
        System.out.println("list" + list);

        // 8. 关闭资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
```

![](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230113000436213.png)

## ==# 3.7 preparedStatement 使用方式总结==

### # 3.7.1 使用步骤总结

1. **注册驱动**
2. **获取连接**
3. **编写SQL语句**
4. **创建preparedstatement并且传入SQL语句结构**
5. **占位符赋值**
6. **发送SQL语句,并且获取结果** 
7. **结果集解析**
8. **关闭资源**

### # 3.7.2 使用 API 总结

```java
//1.注册驱动
方案1: 调用静态方法,但是会注册两次
DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
方案2: 反射触发
Class.forName("com.mysql.cj.jdbc.Driver");

//2.获取连接
Connection connection = DriverManager.getConnection();

3 (String url,String user,String password)
2 (String url,Properties info(user password))
1 (String url?user=账号&password=密码 )

//3.创建statement

//静态
Statement statement = connection.createStatement();
//预编译
PreparedStatement preparedstatement = connection.preparedStatement(sql语句结构);

//4.占位符赋值

preparedstatement.setObject(?的位置 从左到右 从1开始,值)

//5.发送sql语句获取结果

int rows = executeUpdate(); //非DQL
Resultset = executeQuery(); //DQL

//6.查询结果集解析

//移动光标指向行数据 next();  if(next())  while(next())
//获取列的数据即可   get类型(int 列的下角标 从1开始 | int 列的label (别名或者列名))
//获取列的信息   getMetadata(); ResultsetMetaData对象 包含的就是列的信息
                getColumnCount(); | getCloumnLebal(index)
//7.关闭资源
close(); 
```

# # 4. 全新 JDBC 扩展提升

---

## # 4.1 自增长主键回显实现

### # 4.1.1 功能需求

- **java程序获取插入数据时 mysql维护自增长维护的主键 id 值,这就是主键回显。**
- **作用：在多表关联插入数据时，一般主表的主键都是自动生成的，所以在插入数据之前无法知道这条数据的主键，但是从表需要在插入数据之前就绑定主表的主键，这是可以使用主键回显技术。**

### # 4.1.2 功能实现

> ​	继续沿用之前的表数据

```java
/**
     * @param :
     * @return void
     * TODO:
     *  t_user插入一条数据！并且获取数据库自增长的主键！
     *
     *  TODO：使用总结：
     *  1. 创建preparedStatement的时候，告知，携带回数据库自增长的主键（sql，Statement，RETURN_GENERATED_KEYS）
     *  2. 获取司机装主键值的结果集对象，一行一列，获取对应的数据即可     ResultSet resultset = preparedStatement.getGeneratedKeys();
     */

    @Test
    public void returnPrimaryKey() throws ClassNotFoundException, SQLException {

        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?user=root&password=123456");
        //3.编写SQL语句
        String sql = "insert into t_user (account,password,nickname) values (?,?,?);";
        //4.创建statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        //5.占位符赋值
        preparedStatement.setObject(1,"test1");
        preparedStatement.setObject(2,"123456");
        preparedStatement.setObject(3,"林晨曦");

        //6.发送SQL语句，并且获取结果
        int i = preparedStatement.executeUpdate();
        //7.结果解析
        if (i > 0){
            System.out.println("数据插入成功!");

            // 可以获取回显的主键
            // 获取司机装主键的结果集对象，一行 一列
            ResultSet resultset = preparedStatement.getGeneratedKeys();
            resultset.next();//移动下光标！
            int id = resultset.getInt(1);
            System.out.println("id= " + id);
        }else{
            System.out.println("数据插入失败！");
        }
        //8.关闭资源

        preparedStatement.close();
        connection.close();

    }
```

![](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230113012147168.png)

## # 4.2 批量数据插入性能提升

### # 4.2.1 功能需求

- **批量数据插入优化**
- **提升大量数据插入效率**

### # 4.2.2 功能实现

> ​	使用普通的方式插入10000条数据

```java
/**
     * @param :
     * @return void
     * 使用普通的方式插入10000条数据
     */

    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {

        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?user=root&password=123456");
        //3.编写SQL语句
        String sql = "insert into t_user (account,password,nickname) values (?,?,?);";
        //4.创建statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //5.占位符赋值

       long start =  System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {

            preparedStatement.setObject(1,"dd" + i);
            preparedStatement.setObject(2,"dd" + i);
            preparedStatement.setObject(3,"林晨曦" + i );

            //6.发送SQL语句，并且获取结果
            preparedStatement.executeUpdate();

        }
        long end = System.currentTimeMillis();


        //7.结果解析 执行10000次数据插入消耗的时间：46377
        System.out.println("执行10000次数据插入消耗的时间：" + (end - start));
        //8.关闭资源

        preparedStatement.close();
        connection.close();

    }
```

> ​	批量插入数据

```java
/**
     * @param :
     * @return void
     * TODO：总结批量插入
     *  1. 路径后面添加？rewriteBatchedStatements=true 允许批量插入
     *  2. insert into values 【必须写】 语句不能添加;结束
     *  3. 不是执行语句每条，是批量添加 addBatch();
     *  4. 遍历添加完毕后，统一批量执行 executeBatch()
     */



    @Test
    public void testBatchInsert() throws ClassNotFoundException, SQLException {

        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection connection = DriverManager.getConnection(
                // 允许批量操作
                "jdbc:mysql:///atguigu?rewriteBatchedStatements=true","root","123456");
        //3.编写SQL语句
        String sql = "insert into t_user (account,password,nickname) values (?,?,?)";
        //4.创建statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //5.占位符赋值

        long start =  System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {

            preparedStatement.setObject(1,"ddd" + i);
            preparedStatement.setObject(2,"ddd" );
            preparedStatement.setObject(3,"林晨曦dd" + i );

            //6.发送SQL语句，并且获取结果
            // preparedStatement.executeUpdate();
            preparedStatement.addBatch();//不执行，直接追加到value的后面！


        }
        preparedStatement.executeBatch();//执行批量操作！
        // preparedStatement.executeLargeUpdate();//假的，空壳子的
        long end = System.currentTimeMillis();


        //7.结果解析 执行10000次数据插入消耗的时间：248
        System.out.println("执行10000次数据插入消耗的时间：" + (end - start));
        //8.关闭资源

        preparedStatement.close();
        connection.close();

    }
```

## # 4.3 JDBC 中数据库事务实现

### # 4.3.1 章节目标

- 使用 JDBC 代码,添加数据库事务动作!
- 开启事务
- 事务提交 / 事务回滚

### # 4.3.2 事务概念回顾

```markdown
// 事务概念
   数据库事务就是一种SQL语句执行的缓存机制,不会单条执行完毕就更新数据库数据,最终根据缓
   存内的多条语句执行结果统一判定!
   一个事务内所有语句都成功及事务成功,我们可以触发commit提交事务来结束事务,更新数据!
   一个事务内任意一条语句失败,及事务失败,我们可以触发rollback回滚结束事务,
   数据回到事务之前状态!
   
   举个例子: 
           临近高考,你好吃懒做,偶尔还瞎花钱,父母也只会说'你等着!',待到高考完毕!
           成绩600+,翻篇,庆祝!
           成绩200+,翻旧账,男女混合双打!
           
//优势
   允许我们在失败情况下,数据回归到业务之前的状态! 
   
//场景
   一个业务涉及多条修改数据库语句!
   例如: 经典的转账案例,转账业务(加钱和减钱)   
         批量删除(涉及多个删除)
         批量添加(涉及多个插入)     
         
// 事务特性
  1. 原子性（Atomicity）原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。 

  2. 一致性（Consistency）事务必须使数据库从一个一致性状态变换到另外一个一致性状态。

  3. 隔离性（Isolation）事务的隔离性是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。

  4. 持久性（Durability）持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来的其他操作和数据库故障不应该对其有任何影响

// 事务类型
  
  自动提交 : 每条语句自动存储一个事务中,执行成功自动提交,执行失败自动回滚! (MySQL)
  手动提交:  手动开启事务,添加语句,手动提交或者手动回滚即可!
  
// sql开启事务方式
   针对自动提交: 关闭自动提交即可,多条语句添加以后,最终手动提交或者回滚! (推荐)
     
      SET autocommit = off; //关闭当前连接自动事务提交方式
      # 只有当前连接有效
      # 编写SQL语句即可
      SQL
      SQL
      SQL
      #手动提交或者回滚 【结束当前的事务】
      COMMIT / ROLLBACK ;  
     
   手动开启事务: 开启事务代码,添加SQL语句,事务提交或者事务回滚! (不推荐)

// 呼应jdbc技术
 
  try{
    connection.setAutoCommit(false); //关闭自动提交了
    
    //注意,只要当前connection对象,进行数据库操作,都不会自动提交事务
    //数据库动作!
    //statement - 单一的数据库动作 c u r d 
    
    connection.commit();
  }catch(Execption e){
    connection.rollback();
  }
```

### # 4.3.3 数据库表数据

```mysql
-- 继续在atguigu的库中创建银行表
CREATE TABLE t_bank(
   id INT PRIMARY KEY AUTO_INCREMENT COMMENT '账号主键',
   account VARCHAR(20) NOT NULL UNIQUE COMMENT '账号',
   money  INT UNSIGNED COMMENT '金额,不能为负值') ;
   
INSERT INTO t_bank(account,money) VALUES
  ('ergouzi',1000),('lvdandan',1000);
```

![image-20230113022905704](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230113022905704.png)

### # 4.3.4 代码结构设计

![image-20230113020302104](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230113020302104.png)

### # 4.3.5 JDBC 事务实现

> ​	BankDao

```java
package com.atguigu.api.transaction;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.transaction
 * @className: BankDao
 * @author: 林晨曦
 * @description: bank表的数据库操作方法存储类
 * @date: 2023/1/13 2:12
 * @version: 1.0
 */

public class BankDao {

    /**
     * 价钱的数据库操作方法（jdbc）
     *
     * @param account    加钱的行号
     * @param money      加钱的金额
     * @param connection
     * @return void
     * TODO
     */

    @Test
    public void  add(String account, int money, Connection connection) throws ClassNotFoundException, SQLException {

        // 3. 编写 SQL 语句结束
        String sql = "update t_bank set money = money + ? where account = ?";
        // 4. 创建statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        preparedStatement.setObject(1,money);
        preparedStatement.setObject(2,account);
        // 6. 发送 SQL 语句
        int i = preparedStatement.executeUpdate();

        // 7. 关闭资源
        preparedStatement.close();
        // connection.close();

        System.out.println("加钱成功！");

    }

    /**
     * 减钱的数据库操作方法（jdbc）
     *
     * @param account    减钱的行号
     * @param money      减钱的金额
     * @param connection
     * @return null
     * TODO
     */

    @Test
    public void  sub(String account, int money, Connection connection) throws ClassNotFoundException, SQLException {


        // 3. 编写 SQL 语句结束
        String sql = "update t_bank set money = money - ? where account = ?";
        // 4. 创建statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        preparedStatement.setObject(1,money);
        preparedStatement.setObject(2,account);
        // 6. 发送 SQL 语句
        int i = preparedStatement.executeUpdate();

        // 7. 关闭资源
        preparedStatement.close();
        // connection.close();


        System.out.println("减钱成功！");

    }
}
```

> BankService

```java
/**
     * @param addAccount:
     * @param subAccount:
     * @param money:
     * @return void
     * TODO:
     *      事务添加是在业务方法中！
     *      利用 try catch代码块，开始事务和提交事务，和事务回滚
     *      将connection传入dao层即可！dao层只负责使用，不要close！
     */


    public void transfer(String addAccount,String subAccount,int money) throws SQLException, ClassNotFoundException {

        BankDao bankDao = new BankDao();

        // 一个事务的最基本要求，必须是同一个连接对象，connection

        // 一个转账方法，属于一个事务（加钱，减钱）

        // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123456");

        try{
        //开启事务
        //关闭事务提交
        connection.setAutoCommit(false);
        //执行数据库动作
            bankDao.add(addAccount,money,connection);
            System.out.println("------");
            bankDao.sub(subAccount,money,connection);
        //事务提交
        connection.commit();
        }catch (Exception e){
        //事务回滚
         connection.rollback();
         //抛出
         throw e;



        }finally {
            connection.close();
        }

    }
```

> ​	测试类

```java
@Test
    public void start() throws SQLException, ClassNotFoundException {
        // 二狗子 给驴蛋蛋转500
        transfer("lvdandan","ergouzi",500);
    }
```

> ​	测试结果

![image-20230113105551278](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230113105551278.png)

![image-20230113105628162](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230113105628162.png)

​	**一旦钱不够，则不能成功转账，不会出现钱没扣掉，对面还加钱的情况，运行正常！**

# # 5. 国货之光 Druid 连接池技术使用

---

## # 5.1 连接性能消耗问题分析

​	**connection可以复用！**



## # 5.2 数据库连接池作用

​	==**总结缺点：**==

**（1）不使用数据库连接池，每次都通过 DriverManager 获取新连接，用完直接抛弃断开，连接的利用率太低，太浪费。**

**（2）对于数据库服务器来说，压力太大了。我们数据库服务器和 Java 程序对连接数也无法控制，很容易导致数据库服务器崩溃。**

​	==**我们就希望能管理连接。**==

- **我们可以建立一个连接池，这个池中可以容纳一定数量的连接对象，一开始我们可以先替用户先创建好一些连接对象，等用户要拿连接对象时，就直接从池中拿，不用新建了，这样也可以节省时间。然后用户用完后，放回去，别人可以接着用。**
- **可以提高连接的使用率。当池中的现有的连接都用完了，那么连接池可以向服务器申请新的连接放在池中。**
- **直到池中的连接达到“最大连接数”，就不能再申请新的连接了，如果没有拿到连接的用户只能等待。**

## # 5.3 市面常见连接池产品和对比

**JDBC 的数据库连接池使用 javax.sql.DataSource 接口进行规范，==所有==的第三方==连接池都实现此接口==，自行添加具体实现！也就是说，==所有连接池获取连接和回收连接方法都一样==，不同的只有性能和扩展功能！**

- **==DBCP== 是 Apache 提供的数据库连接池，速度相对 c3p0 较快，但因自身存在 ==BUG==。**
- **==C3P0== 是一个开源组织提供的一个数据库连接池，速度相对较慢，稳定性还可以。**
- **==Proxool== 是 sourceforge 下的一个开源项目数据库连接池，有监控连接池状态的功能，稳定性较 c3p0 差一点。**
- **==Druid== 是阿里提供的数据库连接池，据说是集 ==DBCP、C3P0、Proxool== 优点于一身的数据库连接池，妥妥国货之光！！！**

![image-20230113111253287](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230113111253287.png)

![image-20230113111310663](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230113111310663.png)

## # 5.4 国货之光 druid 连接池使用

> ​	记得导入 druid 工具类 jar

![image-20230113111757003](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230113111757003.png)

### # 5.4.1 硬编码方式（了解，不推荐）

```java
public class DruidUsePart {

    /**
     * @param :
     * @return void
     * 直接使用代码块设置连接池连接参数方式！
     *
     * 1.创建一个druid连接池对象
     *
     * 2.设置连接池参数【必须 | 非必须】
     *
     * 3.获取连接【通用方法，所有连接池都一样】
     *
     * 4.回收连接【通用方法，所有连接池都一样】
     */

    public void testHard() throws SQLException {


        // 连接池对象
        DruidDataSource dataSource = new DruidDataSource();

        // 设置参数
        // 必须 连接数据库驱动类的全限定符【注册驱动】 url | user | password
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");//帮助我们驱动注册和获取连接
        // 非必须 初始化连接数量，最大的连接数量......

        dataSource.setInitialSize(5); //初始化连接数量
        dataSource.setMaxActive(10); //最大的连接数量

        // 获取连接
        DruidPooledConnection connection = dataSource.getConnection();

        //数据库curd

        //回收连接
        connection.close();//连接池提供的连接，close，就是回收连接


    }
}
```

### # 5.4.2 软编码方式

- **外部配置，存放在 src/druid.properties**

```properties
# druid连接池需要的配置参数,key固定命名
driverClassName=com.mysql.cj.jdbc.Driver
username=root
password=root
url=jdbc:mysql:///atguigu
```

- **druid 声明代码**

```java
 /**
     * @param :
     * @return void
     * 通过读取外部配置文件的方法，实例化druid连接池对象
     */

    public void testSoft() throws Exception {

        //1. 读取外部配置文件 Properties

        Properties properties = new Properties();
        // src下的文件，可以使用类加载器提供的方法
        InputStream ips = DruidUsePart.class.getClassLoader().getResourceAsStream("druid.properties");

        properties.load(ips);
        //2. 使用连接池的工具类的工程模式，创建连接池

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        // 数据库crud
        connection.close();

    }
```

- **druid 配置（了解）**

|             配置              | 缺省  |                             说明                             |
| :---------------------------: | :---: | :----------------------------------------------------------: |
|             name              |       | 配置这个属性的意义在于，如果存在多个数据源，监控的时候可以通过名字来区分开来。 如果没有配置，将会生成一个名字，格式是：”DataSource-” + System.identityHashCode(this) |
|            jdbcUrl            |       | 连接数据库的url，不同数据库不一样。例如：mysql : jdbc:mysql://10.20.153.104:3306/druid2 oracle : jdbc:oracle:thin:@10.20.149.85:1521:ocnauto |
|           username            |       |                      连接数据库的用户名                      |
|           password            |       | 连接数据库的密码。如果你不希望密码直接写在配置文件中，可以使用ConfigFilter。详细看这里：[https://github.com/alibaba/druid/wiki/%E4%BD%BF%E7%94%A8ConfigFilter](https://github.com/alibaba/druid/wiki/使用ConfigFilter) |
|        driverClassName        |       | 根据url自动识别 这一项可配可不配，如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName(建议配置下) |
|          initialSize          |   0   | 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时 |
|           maxActive           |   8   |                        最大连接池数量                        |
|            maxIdle            |   8   |                 已经不再使用，配置了也没效果                 |
|            minIdle            |       |                        最小连接池数量                        |
|            maxWait            |       | 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。 |
|    poolPreparedStatements     | false | 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。 |
|   maxOpenPreparedStatements   |  -1   | 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100 |
|        validationQuery        |       | 用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。 |
|         testOnBorrow          | true  | 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 |
|         testOnReturn          | false | 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能 |
|         testWhileIdle         | false | 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。 |
| timeBetweenEvictionRunsMillis |       | 有两个含义： 1)Destroy线程会检测连接的间隔时间2)testWhileIdle的判断依据，详细看testWhileIdle属性的说明 |
|    numTestsPerEvictionRun     |       |      不再使用，一个DruidDataSource只支持一个EvictionRun      |
|  minEvictableIdleTimeMillis   |       |                                                              |
|      connectionInitSqls       |       |                物理连接初始化的时候执行的sql                 |
|        exceptionSorter        |       | 根据dbType自动识别 当数据库抛出一些不可恢复的异常时，抛弃连接 |
|            filters            |       | 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有： 监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall |
|         proxyFilters          |       | 类型是List，如果同时配置了filters和proxyFilters，是组合关系，并非替换关系 |

# # 6. 全新 JDBC 使用优化以及工具类封装

---

## # 6.1 JDBC 工具类封装v1.0

> ​	我们封装一个工具类，内部包含连接池对象，同时对外提供连接的方法和回收连接的方法！

​	**外部配置文件，位置：src/druid.properties**

```properties
# druid连接池需要的配置参数,key固定命名
driverClassName=com.mysql.cj.jdbc.Driver
username=root
password=root
url=jdbc:mysql:///atguigu
```

​	**工具类代码**

```java
/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.utils
 * @className: JdbcUtils
 * @author: 林晨曦
 * @description:
 *  v1.0版本工具类
 *      内部包含一个连接池对象，并且对外提供获取连接和回收连接的方法！
 *
 *  小建议：
 *      工具类的方法，推荐写成静态，外部调用会更加方便！
 *
 *  实现：
 *      属性 连接池对象【实例化一次】
 *          单例模式
 *          static{
 *
 *                 全局调用一次
 *          }
 *      方法
 *          对外提供连接的方法
 *          回收外部传入连接方法
 * @date: 2023/1/13 11:50
 * @version: 1.0
 */

public class JdbcUtils {

    // 连接池对象
    private static DataSource dataSource = null;

    static{

    // 初始化连接池对象

        Properties properties = new Properties();
        InputStream ips = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param :
     * @return Connection
     * 对外提供连接的方法
     */

    public static Connection getConnection() throws SQLException {

        return dataSource.getConnection();
    }


    public static void freeConnection(Connection connection) throws SQLException {

       connection.close();//连接池的连接，调用close就是释放
    }

}
```

​	**测试类代码**

```java
public void testInsert() throws SQLException {

        Connection connection = JdbcUtils.getConnection();

    //     数据库的curd动作
        JdbcUtils.freeConnection(connection);

    }
```

## # 6.2 JDBC 工具类封装v2.0

> ​	优化了工具类v1.0版本，考虑事务的情况下！如何一个线程的不同方法获取同一个连接！

​	**ThreadLocal的介绍：**

​	**==JDK 1.2==的版本中就提供 java.lang.ThreadLocal，为解决多线程程序的并发问题提供了一种新的思路。使用这个工具类可以简洁地编写出优美的多线程程序。通常用来在多线程中管理共享数据库连接、Session等。**

​	**ThreadLocal 用于保存某个线程共享变量，原因是 Java 中，每一个线程对象中都有一个 ThreadLocalMap\<ThreadLocal,Object>，其 key就是一个 ThreadLocal，而Object即为该线程的共享变量。而这个map是通过ThreadLocal 的 set 和 get 方法操作的。对于同一个 ==static== ThreadLocal，不同线程只能从中 get，set，remove自己的变量，而不会影响其他线程的变量。**

- **ThreadLocal 对象.get：获取 ThreadLocal 中当前线程共享变量的值。**
- **ThreadLocal对象.set：设置 ThreadLocal 中当前线程共享变量的值。**
- **ThreadLocal对象.remove：移除 ThreadLocal中当前线程共享变量的值。**

​	**v2.0版本工具类**

```java
package com.atguigu.api.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.utils
 * @className: JdbcUtils
 * @author: 林晨曦
 * @description:
 *  v1.0版本工具类
 *      内部包含一个连接池对象，并且对外提供获取连接和回收连接的方法！
 *
 *  小建议：
 *      工具类的方法，推荐写成静态，外部调用会更加方便！
 *
 *  实现：
 *      属性 连接池对象【实例化一次】
 *          单例模式
 *          static{
 *
 *                 全局调用一次
 *          }
 *      方法
 *          对外提供连接的方法
 *          回收外部传入连接方法
 * @date: 2023/1/13 11:50
 * @version: 1.0
 *
 * TODO:
 *  利用线程本地变量，存储连接信息！确保一个线程的多个方法可以获取同一个connection！
 *  优势：事务操作的时候 service 和 dao 属于同一个线程，不同再传递参数了！
 *  大家都可以调用getConnection自动获取的是相同的连接池！
 *
 *
 */

public class JdbcUtilsV2 {

    // 连接池对象
    private static DataSource dataSource ;

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static{

    // 初始化连接池对象

        Properties properties = new Properties();
        // InputStream ips = JdbcUtilsV2.class.getClassLoader().getResourceAsStream("druid.properties");
        // InputStream ips = JdbcUtilsV2.class.getResourceAsStream("druid.properties");
        InputStream ips = ClassLoader.getSystemResourceAsStream("druid.properties");

        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param :
     * @return Connection
     * 对外提供连接的方法
     */

    public static Connection getConnection() throws SQLException {

        //线程本地变量中是否存在
        Connection connection = tl.get();

        // 第一次没有

        if(connection == null){

            // 线程本地变量没有，连接池获取
            // 这里是错误的
            // Connection connection1 = dataSource.getConnection();
            connection = dataSource.getConnection();

            tl.set(connection);


        }

        // return dataSource.getConnection();
        return connection;
    }


    public static void freeConnection() throws SQLException {

        Connection connection = tl.get();
        if(connection != null){
            tl.remove();//清空线程本地变量数据
            connection.setAutoCommit(true);
            connection.close();//回收到连接池即可
        }


    }

}
```

**==注意：==修改转账业务，使用此工具类。**

> ​	BankDao

```java
public class BankDao {

    /**
     * 价钱的数据库操作方法（jdbc）
     *
     * @param account    加钱的行号
     * @param money      加钱的金额
     * @param connection
     * @return void
     * TODO
     */

    @Test
    public void  add(String account, int money) throws ClassNotFoundException, SQLException {

        Connection connection = JdbcUtilsV2.getConnection();
        // 3. 编写 SQL 语句结束
        String sql = "update t_bank set money = money + ? where account = ?";
        // 4. 创建statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        preparedStatement.setObject(1,money);
        preparedStatement.setObject(2,account);
        // 6. 发送 SQL 语句
        int i = preparedStatement.executeUpdate();

        // 7. 关闭资源
        preparedStatement.close();
        // connection.close();

        System.out.println("加钱成功！");

    }

    /**
     * 减钱的数据库操作方法（jdbc）
     *
     * @param account    减钱的行号
     * @param money      减钱的金额
     * @param connection
     * @return null
     * TODO
     */

    @Test
    public void  sub(String account, int money) throws ClassNotFoundException, SQLException {


        Connection connection = JdbcUtilsV2.getConnection();
        // 3. 编写 SQL 语句结束
        String sql = "update t_bank set money = money - ? where account = ?";
        // 4. 创建statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        preparedStatement.setObject(1,money);
        preparedStatement.setObject(2,account);
        // 6. 发送 SQL 语句
        int i = preparedStatement.executeUpdate();

        // 7. 关闭资源
        preparedStatement.close();
        // connection.close();


        System.out.println("减钱成功！");

    }
}
```

> ​	BankService

```java
public class BankService {

    @Test
    public void start() throws SQLException, ClassNotFoundException {
        // 二狗子 给驴蛋蛋转500
        transfer("lvdandan","ergouzi",500);
    }


    /**
     * @param addAccount:
     * @param subAccount:
     * @param money:
     * @return void
     * TODO:
     *      事务添加是在业务方法中！
     *      利用 try catch代码块，开始事务和提交事务，和事务回滚
     *      将connection传入dao层即可！dao层只负责使用，不要close！
     */


    public void transfer(String addAccount,String subAccount,int money) throws SQLException, ClassNotFoundException {

        BankDao bankDao = new BankDao();

        // 一个事务的最基本要求，必须是同一个连接对象，connection

        // 一个转账方法，属于一个事务（加钱，减钱）

        Connection connection = JdbcUtilsV2.getConnection();

        try{
        //开启事务
        //关闭事务提交
        connection.setAutoCommit(false);
        //执行数据库动作
            bankDao.add(addAccount,money);
            System.out.println("------");
            bankDao.sub(subAccount,money);
        //事务提交
        connection.commit();
        }catch (Exception e){
        //事务回滚
         connection.rollback();
         //抛出
         throw e;



        }finally {
            JdbcUtilsV2.freeConnection();

        }


    }
}
```

## # 6.3 高级应用层封装 BaseDao

> ​	基本上每一个数据表都应该有一个对应的 DAO 接口及其实现类，发现对所有表的操作（增、删、改、查）代码重复度很高，所以可以==抽取公共代码==，给这些DAO的实现类可以抽取一个公共的父类，我们称为 BaseDAO。

```java
/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.utils
 * @className: BaseDao
 * @author: 林晨曦
 * @description: 封装 dao数据库重复代码！
 *
 * TODO：
 *      封装两个方法      一个简化非DQL
 *                       一个简化DQL
 * @date: 2023/1/13 21:53
 * @version: 1.0
 *
 *
 */

/**
 * @param sql 带占位符的SQL语句
 * @param params 占位符的值 注意，传入占位符的值，必须等于SQL语句？位置！
 * @return 执行影响的行数
 * 封装简化 非DQL语句
 */

public  abstract class BaseDao {

    public int executeUpdate(String sql, Object... params ) throws SQLException {

        //获取连接
        Connection connection = JdbcUtilsV2.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 占位符赋值
        //可变参数可以当做数组使用

        for (int i = 1; i <= params.length; i++) {

            preparedStatement.setObject(i,params[i-1]);
            
        }
    //     发送 SQL 语句
        //DML类型

        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();

        // 是否回收连接，需要考虑是不是事务！

        if(connection.getAutoCommit()){
        //     没有开启事务
        //     没有开启事务，正常回收连接啦！
            JdbcUtilsV2.freeConnection();
        }
        // connection.setAutoCommit(false);//开启事务了，不要管连接即可！业务层处理！
        // connection.close();//不一定合适

        return rows;

    }

    /**
     * @param null:
     * @return null
     *
     *  非DQL 语句封装方法 ->返回值 固定为int
     *
     *  DQL语句封装方法 ->返回值 是什么类型呢？？？  List<T> list
     *
     *
     *          并不是list<map> map key 和 value 自定义！ 不用先设定好
     *          map 没有数据校验机制
     *          map 不支持反射操作
     *
     *          数据库数据 -> java的实体类
     *          table
     *              t_user
     *              id
     *              account
     *              password
     *              nickname
     *          java
     *              User
     *              id
     *              account
     *              password
     *              nickname
     *          表中 -> 一行 -> java类的一个对象   -> 多行  List<java实体类> list;
     *
     *      DQL-> List<map> -> 一行 -> map -> List<map>
     *
     *  <T>声明一个泛型，不确定类型
     *  1.确定泛型 User.class T =User
     *  2.要使用反射技术属性赋值
     *  public <T>  List<不确定> executeQuery(Class<T> clazz,String sql,Object... params);
     *
     *
     *  将查询结果封装到一个实体类集合！
     * @param clazz 要接值的实体类集合的模板对象
     * @param sql 查询语句，要求列名或别名等于实体类的属性名！u_id as uId => uId
     *
     * @param params 占位符的值，要和?位置对应传递
     * @param <T>  声明的结果的类型！
     */



    public <T>  List<T> executeQuery(Class<T> clazz,String sql,Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        // 获取连接
        Connection connection = JdbcUtilsV2.getConnection();


        // 4. 创建preparedStatement，并且传入 SQL 语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        if (params == null && params.length != 0) {
            for (int i = 1; i <= params.length; i++) {

                preparedStatement.setObject(i, params[i - 1]);

            }
        }


        // 6. 发送 SQL 语句
        ResultSet resultSet = preparedStatement.executeQuery();
        // 7. 结果集解析



        List<T> list = new ArrayList<>();
        // 获取列的信息对象
        // TODO: metaData 装的当前结果集列的信息对象！（他可以获取列的名称根据下角标，可以获取列的数量）
        ResultSetMetaData metaData = resultSet.getMetaData();


        // 有了它以后，我们可以水平遍历列！
        int columnCount = metaData.getColumnCount();


        while (resultSet.next()) {

            T t = clazz.newInstance();//调用类无参构造函数实例化对象！
            // Map map = new HashMap();
            //     一行数据对应一个 T类型的对象
            //     纯手动取值



            //     自动遍历列
            // 自动遍历 注意，要从1开始，并且小于等于总列数！
            for (int i = 1; i <= columnCount; i++) {
                // 对象的属性值
                Object value = resultSet.getObject(i);

                // 对象的属性名
                String propertyName = metaData.getColumnLabel(i);

            //     反射，给对象的属性值赋值
                Field declaredField = clazz.getDeclaredField(propertyName);

                declaredField.setAccessible(true);//属性可以设置，打破private的修饰限制

               /**
                * @param clazz:
                * @param sql:
                * @param params:
                * @return List<T>
                * 参数1：要赋值的对象，如果属性是静态，第一个参数，可以为null
                * 参数2：具体的属性值
                */

                declaredField.set(t,value);
            }

            //     一行数据的所有列全部存到了map中！
            //     将map存储到集合中即可
            list.add(t);
        }
        // 关闭资源
        resultSet.close();
        preparedStatement.close();
        if(connection.getAutoCommit()){
        //     没有事务可以关闭
            JdbcUtilsV2.freeConnection();
        }
        return list;
    }

}

```

> ​	PSDURDPart.java

```java
package com.atguigu.api.utils;

import com.atguigu.api.transactionnew.BankDao;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.preparedstatement
 * @className: PSCURDPart
 * @author: 林晨曦
 * @description: 使用preparedStatement 进行 t_user 表的 curd 动作
 * @date: 2023/1/12 17:37
 * @version: 1.0
 */

public class PSCURDPart  extends  BaseDao{

    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
    /**
     * @param :
     * @return void
     * account test
     * password test
     * nickname 林晨曦
     */

    // 3. 编写 SQL 语句结果，动态值的部分使用？代替
        String sql = "insert into t_user(account,password,nickname) values(?,?,?)";


        int i = executeUpdate(sql, "测试333", "3333", "林晨曦");
        System.out.println(i);

    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {

        /**
         * @param :
         * @return void
         * 修改 id =3 的用户 nickname = 吴晨曦
         */



        // 3. 编写 SQL 语句结果，动态值的部分使用？代替
        String sql = "update  t_user set nickname = ?  where id = ?";

        int i = executeUpdate(sql, "林晨曦", 2);
        System.out.println(i);

    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
    /**
     * @param :
     * @return void
     * 删除 id =3 的用户数据
     */


        // 3. 编写 SQL 语句结果，动态值的部分使用？代替
        String sql = "delete  from  t_user   where id = ?";

        int i = executeUpdate(sql,3);
        System.out.println(i);

    }

    @Test
    public void testSelect() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
    /**
     * @param :
     * @return void
     * 查询所有用户数据，并且封装到一个List<Map> list集合中！
     *
     * 解释：
     *      行  id  account password nickname
     *      行  id  account password nickname
     *
     *  数据库 -> resultSet -> java -> 一行 -map(key=列名，value=列的内容) -> List<Map> list
     *
     *
     *  实现思路：
     *      遍历行数据，一行对应一个map！获取一行的列名和对应的列的属性，装配即可！
     *      将map装到一个集合就可以了！
     *
     *  难点：
     *      如何获取列的名称？
     */



        // 3. 编写 SQL 语句结果，动态值的部分使用？代替
        String sql = "select id,account,password,nickname   from t_user";


        List<PSCURDPart> pscurdParts = executeQuery(PSCURDPart.class, sql);


        System.out.println( pscurdParts);

    }

}
```

# # 7. 基于 CMS 项目 JDBC 实战练习

---

## # 7.1 cms 项目介绍和导入

### # 7.1.1 项目介绍

> ​	利用 JavaSE 技术，进行控制台输出的客户管理系统！主要功能让包含客户展示，客户删除，客户添加，客户修改，退出系统！

​	**添加客户**

![image-20230114142726014](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230114142726014.png)

![image-20230114142749687](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230114142749687.png)

​	**修改客户**

![image-20230114143003065](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230114143003065.png)

![image-20230114142922540](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230114142922540.png)

​	**展示客户列表**

![image-20230114142944522](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230114142944522.png)

​	**删除客户**

![image-20230114143036416](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230114143036416.png)

![image-20230114143053523](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230114143053523.png)

​	**退出系统**

![image-20230114143136685](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/JDBC/image-20230114143136685.png)

## # 7.2 基于 cms 项目添加数据库相关配置

### # 7.2.1 准备数据库脚本

```mysql
-- 员工表

CREATE TABLE t_customer(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '客户主键',
  NAME VARCHAR(20)  COMMENT '客户名称',
  gender VARCHAR(4) COMMENT '客户性别',
  age INT  COMMENT '客户年龄',
  salary DOUBLE(8,1) COMMENT '客户工资',
  phone VARCHAR(11) COMMENT '客户电话')
```

### # 7.2.2 添加配置文件

> ​	位置：src下，druid.properties

```properties
# druid连接池需要的配置参数,key固定命名
driverClassName=com.mysql.jdbc.Driver
username=root
password=123456
url=jdbc:mysql:///atguigu
```

### # 7.2.3 导入 JDBCv2.0工具类

```java
package com.atguigu.cms.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author 赵伟风
 * Description:
 *
 * v1.0版本工具类
 *   内部包含一个连接池对象,并且对外提供获取连接和回收连接的方法!
 *
 * 小建议:
 *   工具类的方法,推荐写成静态,外部调用会更加方便!
 *
 * 实现:
 *   属性 连接池对象 [实例化一次]
 *       单例模式
 *       static{
 *           全局调用一次
 *       }
 *   方法
 *       对外提供连接的方法
 *       回收外部传入连接方法
 *
 *
 * TODO:
 *    利用线程本地变量,存储连接信息! 确保一个线程的多个方法可以获取同一个connection!
 *    优势: 事务操作的时候 service 和 dao 属于同一个线程,不同再传递参数了!
 *    大家都可以调用getConnection自动获取的是相同的连接池!
 *
 */
public class JdbcUtilsV2 {


    private static DataSource dataSource = null; //连接池对象

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();


    static{
        //初始化连接池对象
        Properties properties = new Properties();
        InputStream ips = JdbcUtilsV2.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对外提供连接的方法
     * @return
     */
    public static Connection getConnection() throws SQLException {

        //线程本地变量中是否存在
        Connection connection = tl.get();

        //第一次没有
        if (connection == null) {
            //线程本地变量没有,连接池获取
            connection = dataSource.getConnection();
            tl.set(connection);
        }

        return  connection;
    }

    public static void freeConnection() throws SQLException {

        Connection connection = tl.get();
        if (connection != null) {
            tl.remove(); //清空线程本地变量数据
            connection.setAutoCommit(true); //事务状态回顾 false
            connection.close(); //回收到连接池即可
        }

    }

}

```

### # 7.2.4 导入 BaseDao 工具类

```java
package com.atguigu.api.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.utils
 * @className: BaseDao
 * @author: 林晨曦
 * @description: 封装 dao数据库重复代码！
 *
 * TODO：
 *      封装两个方法      一个简化非DQL
 *                       一个简化DQL
 * @date: 2023/1/13 21:53
 * @version: 1.0
 *
 *
 */

/**
 * @param sql 带占位符的SQL语句
 * @param params 占位符的值 注意，传入占位符的值，必须等于SQL语句？位置！
 * @return 执行影响的行数
 * 封装简化 非DQL语句
 */

public  abstract class BaseDao {

    public int executeUpdate(String sql, Object... params ) throws SQLException {

        //获取连接
        Connection connection = JdbcUtilsV2.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 占位符赋值
        //可变参数可以当做数组使用

        for (int i = 1; i <= params.length; i++) {

            preparedStatement.setObject(i,params[i-1]);
            
        }
    //     发送 SQL 语句
        //DML类型

        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();

        // 是否回收连接，需要考虑是不是事务！

        if(connection.getAutoCommit()){
        //     没有开启事务
        //     没有开启事务，正常回收连接啦！
            JdbcUtilsV2.freeConnection();
        }
        // connection.setAutoCommit(false);//开启事务了，不要管连接即可！业务层处理！
        // connection.close();//不一定合适

        return rows;

    }

    /**
     * @param null:
     * @return null
     *
     *  非DQL 语句封装方法 ->返回值 固定为int
     *
     *  DQL语句封装方法 ->返回值 是什么类型呢？？？  List<T> list
     *
     *
     *          并不是list<map> map key 和 value 自定义！ 不用先设定好
     *          map 没有数据校验机制
     *          map 不支持反射操作
     *
     *          数据库数据 -> java的实体类
     *          table
     *              t_user
     *              id
     *              account
     *              password
     *              nickname
     *          java
     *              User
     *              id
     *              account
     *              password
     *              nickname
     *          表中 -> 一行 -> java类的一个对象   -> 多行  List<java实体类> list;
     *
     *      DQL-> List<map> -> 一行 -> map -> List<map>
     *
     *  <T>声明一个泛型，不确定类型
     *  1.确定泛型 User.class T =User
     *  2.要使用反射技术属性赋值
     *  public <T>  List<不确定> executeQuery(Class<T> clazz,String sql,Object... params);
     *
     *
     *  将查询结果封装到一个实体类集合！
     * @param clazz 要接值的实体类集合的模板对象
     * @param sql 查询语句，要求列名或别名等于实体类的属性名！u_id as uId => uId
     *
     * @param params 占位符的值，要和?位置对应传递
     * @param <T>  声明的结果的类型！
     */



    public <T>  List<T> executeQuery(Class<T> clazz,String sql,Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        // 获取连接
        Connection connection = JdbcUtilsV2.getConnection();


        // 4. 创建preparedStatement，并且传入 SQL 语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        if (params == null && params.length != 0) {
            for (int i = 1; i <= params.length; i++) {

                preparedStatement.setObject(i, params[i - 1]);

            }
        }


        // 6. 发送 SQL 语句
        ResultSet resultSet = preparedStatement.executeQuery();
        // 7. 结果集解析



        List<T> list = new ArrayList<>();
        // 获取列的信息对象
        // TODO: metaData 装的当前结果集列的信息对象！（他可以获取列的名称根据下角标，可以获取列的数量）
        ResultSetMetaData metaData = resultSet.getMetaData();


        // 有了它以后，我们可以水平遍历列！
        int columnCount = metaData.getColumnCount();


        while (resultSet.next()) {

            T t = clazz.newInstance();//调用类无参构造函数实例化对象！
            // Map map = new HashMap();
            //     一行数据对应一个 T类型的对象
            //     纯手动取值



            //     自动遍历列
            // 自动遍历 注意，要从1开始，并且小于等于总列数！
            for (int i = 1; i <= columnCount; i++) {
                // 对象的属性值
                Object value = resultSet.getObject(i);

                // 对象的属性名
                String propertyName = metaData.getColumnLabel(i);

            //     反射，给对象的属性值赋值
                Field declaredField = clazz.getDeclaredField(propertyName);

                declaredField.setAccessible(true);//属性可以设置，打破private的修饰限制

               /**
                * @param clazz:
                * @param sql:
                * @param params:
                * @return List<T>
                * 参数1：要赋值的对象，如果属性是静态，第一个参数，可以为null
                * 参数2：具体的属性值
                */

                declaredField.set(t,value);
            }

            //     一行数据的所有列全部存到了map中！
            //     将map存储到集合中即可
            list.add(t);
        }
        // 关闭资源
        resultSet.close();
        preparedStatement.close();
        if(connection.getAutoCommit()){
        //     没有事务可以关闭
            JdbcUtilsV2.freeConnection();
        }
        return list;
    }

}

```

## # 7.3 基于 cms 项目实战

### # 7.3.1 customerService

```
```

### # 7.3.2 customerDao

```
```


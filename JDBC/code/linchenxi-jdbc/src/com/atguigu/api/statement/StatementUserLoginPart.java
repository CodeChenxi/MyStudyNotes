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
         *                      加载 [class 文件 -> java 虚拟机的 class 对象 ]
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
        // DriverManager.registerDriver(new com.mysql.jdbc.Driver());

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
         *                 	如果有多个，可以用 &连接
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

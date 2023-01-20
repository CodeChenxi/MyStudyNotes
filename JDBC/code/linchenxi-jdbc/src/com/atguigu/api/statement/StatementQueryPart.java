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
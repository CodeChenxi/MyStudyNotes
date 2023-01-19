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

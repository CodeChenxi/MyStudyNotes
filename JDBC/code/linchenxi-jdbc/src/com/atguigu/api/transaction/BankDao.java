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

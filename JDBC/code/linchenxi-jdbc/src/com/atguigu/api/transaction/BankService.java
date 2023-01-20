package com.atguigu.api.transaction;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.transaction
 * @className: BankService
 * @author: 林晨曦
 * @description: 银行卡业务方法，调用dao方法
 * @date: 2023/1/13 2:16
 * @version: 1.0
 */

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
}

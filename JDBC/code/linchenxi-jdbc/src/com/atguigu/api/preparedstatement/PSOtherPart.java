package com.atguigu.api.preparedstatement;


import org.junit.Test;

import java.sql.*;

/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.preparedstatement
 * @className: PSOtherPart
 * @author: 林晨曦
 * @description: TODO
 * @date: 2023/1/13 1:00
 * @version: 1.0
 */

public class PSOtherPart {

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
}

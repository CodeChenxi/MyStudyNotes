package com.atguigu.api.preparedstatement;

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

public class PSCURDPart {

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


}

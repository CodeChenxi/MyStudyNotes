package com.atguigu.api.utils;

import com.atguigu.api.bean.User;

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



    protected  <T>  List<T> executeQuery(Class<T> clazz,String sql,Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {

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

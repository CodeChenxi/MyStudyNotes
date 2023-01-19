package com.atguigu.api.utils;

import com.atguigu.api.bean.User;
import com.atguigu.api.transactionnew.BankDao;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
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
    public void testSelect() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
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
        // String sql = "select account,password,nickname   from t_user";
        String sql = "select * from t_user";


        List<PSCURDPart> list = executeQuery(PSCURDPart.class, sql);


        System.out.println(list);


    }


}

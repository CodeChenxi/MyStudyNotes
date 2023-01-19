package com.atguigu.api.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.utils
 * @className: JdbcCrudPart
 * @author: 林晨曦
 * @description: 基于工具类的curd
 * @date: 2023/1/13 16:38
 * @version: 1.0
 */

public class JdbcCrudPart {


    public void testInsert() throws SQLException {

        Connection connection = JdbcUtils.getConnection();

    //     数据库的curd动作
        JdbcUtils.freeConnection(connection);


    }


}

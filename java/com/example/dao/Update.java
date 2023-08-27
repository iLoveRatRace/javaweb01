package com.example.dao;

import com.example.pojo.Article_Detail;
import com.example.pojo.User_Detail;
import com.example.pojo.cP_User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author xiaojin
 * @version 1.0
 */
public class Update {
    public boolean updatePassword(cP_User cP_user) throws ClassNotFoundException, IOException, SQLException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //定义查询的sql语句
        String sql01 = "select * from test_user where db_userName =? " +
                        "and db_password =?";


        PreparedStatement preparedStatement01 = connection.prepareStatement(sql01);

        preparedStatement01.setString(1, cP_user.getUserName());
        preparedStatement01.setString(2, cP_user.getOld_password());

        ResultSet resultSet = preparedStatement01.executeQuery();
        if (resultSet.next()) {
            //定义修改的sql语句
            String sql02 = "update test_user set db_password =?" +
                            "where db_userName =?";
            PreparedStatement preparedStatement02 = connection.prepareStatement(sql02);

            preparedStatement02.setString(1, cP_user.getNew_password());
            preparedStatement02.setString(2, cP_user.getUserName());

            int i = preparedStatement02.executeUpdate();
            return i > 0;
        }

        return false;
    }

    public boolean updateUserDetail(User_Detail user_detail) throws IOException, ClassNotFoundException, SQLException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //定义修改的sql语句
        String sql = "update test_user_detail " +
                        "set " +
                        "sex =?," +
                        "introduce =?," +
                        "birthday =?" +
                        "where (user_name =?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, user_detail.getSex());
        preparedStatement.setString(2, user_detail.getIntroduce());
        preparedStatement.setString(3, user_detail.getBirthday());
        preparedStatement.setString(4, user_detail.getUserName());

//        System.out.println(sql);
        int i = preparedStatement.executeUpdate();
        return i > 0;

    }

    public boolean updatArticleDetail(Article_Detail article_detail) throws IOException, ClassNotFoundException, SQLException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //定义修改的sql语句
        String sql = "update test_article_detail " +
                "set article_detail =? where (article_id =?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, article_detail.getDetail());
        preparedStatement.setString(2, article_detail.getId());

        return preparedStatement.executeUpdate() > 0;

    }
}

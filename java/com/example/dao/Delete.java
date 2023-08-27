package com.example.dao;

import com.example.pojo.Follower_Followee;
import com.example.pojo.User_Article;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author xiaojin
 * @version 1.0
 */
public class Delete {
    public boolean deleteCollection(User_Article user_article) throws IOException, ClassNotFoundException, SQLException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //定义sql语句,分别在两张表上插入数据
        String sql = "delete from test_user_collection where userName =? and article_id =?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user_article.getUserName());
        preparedStatement.setString(2,user_article.getId());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteFollow(Follower_Followee follower_followee) throws IOException, ClassNotFoundException, SQLException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //定义sql语句,分别在两张表上插入数据
        String sql = "delete from test_user_follow where follower_name =? and followee_name =?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,follower_followee.getFollower());
        preparedStatement.setString(2,follower_followee.getFollowee());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteUser(String userName) throws IOException, ClassNotFoundException, SQLException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //开启事务，保证删除操作同时成功都能操作成功
        connection.setAutoCommit(false);

        //删除该用户的个人信息
        String sql01 = "delete from test_user_detail where user_name =?";
        PreparedStatement preparedStatement01 = connection.prepareStatement(sql01);
        preparedStatement01.setString(1,userName);
        int num01 = preparedStatement01.executeUpdate();

        //删除该用户在数据库中的用户名密码
        String sql02 = "delete from test_user where db_userName =?";
        PreparedStatement preparedStatement02 = connection.prepareStatement(sql02);
        preparedStatement02.setString(1,userName);
        int num02 = preparedStatement02.executeUpdate();


        //问题：以下部分的信息不一定每一个用户都有，即不一定每个用户都有收藏、关注、发布等信息

        //删除该用户的关注、被关注信息
        String sql03 = "delete from test_user_follow " +
                "where follower_name =? or followee_name =?";
        PreparedStatement preparedStatement03 = connection.prepareStatement(sql03);
        preparedStatement03.setString(1,userName);
        preparedStatement03.setString(2,userName);

        //删除该用户的收藏文章信息
        String sql04 = "delete from test_user_collection where userName =?";
        PreparedStatement preparedStatement04 = connection.prepareStatement(sql04);
        preparedStatement04.setString(1,userName);

        //查询该用户发布的文章并将作者改为用户已封禁
        String sql05 = "select * from test_article where article_writer =?";
        PreparedStatement preparedStatement05 = connection.prepareStatement(sql05);
        preparedStatement05.setString(1,userName);
        ResultSet resultSet = preparedStatement05.executeQuery();
        List<String> idList = new ArrayList<>();
        while (resultSet.next()){
            //将查询到的结果添加到列表
            idList.add(resultSet.getString(1));
        }
//        System.out.println(idList);

        int i = 0;
        while (i < idList.size()){
            String sql06 = "update test_article " +
                    "set article_writer =?" +
                    "where article_id =?";
            PreparedStatement preparedStatement06 = connection.prepareStatement(sql06);
            preparedStatement06.setString(1,"该用户已注销");
            preparedStatement06.setString(2,idList.get(i++));
            preparedStatement06.executeUpdate();
//            System.out.println("chenggong1ci");
        }


        if(num01 > 0 && num02 > 0){
            connection.commit();
            return true;
        }else {
            connection.rollback();
            return false;
        }
    }

    public boolean deleteArticle(String id) throws IOException, ClassNotFoundException, SQLException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //定义sql语句,分别在两张表上插入数据
        String sql01 = "delete from test_article where article_id =?";
        String sql02 = "delete from test_article_detail where article_id =?";

        PreparedStatement preparedStatement01 = connection.prepareStatement(sql01);
        PreparedStatement preparedStatement02 = connection.prepareStatement(sql02);
        preparedStatement01.setString(1,id);
        preparedStatement02.setString(1,id);

        return preparedStatement01.executeUpdate() > 0 && preparedStatement02.executeUpdate() > 0;
    }
}

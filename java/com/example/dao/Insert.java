package com.example.dao;

import com.example.pojo.Article;
import com.example.pojo.Follower_Followee;
import com.example.pojo.User;
import com.example.pojo.User_Article;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * @author xiaojin
 * @version 1.0
 */
public class Insert {
    public boolean InsertUser(User u) throws IOException, ClassNotFoundException, SQLException {
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
        String sql01 = "insert into test_user values (?,?)";
        String sql02 = "insert into test_user_detail values (?,null,null,null,null)";

        //开启事务，保证两个插入都能操作成功
        connection.setAutoCommit(false);

        PreparedStatement preparedStatement01 = connection.prepareStatement(sql01);
        PreparedStatement preparedStatement02 = connection.prepareStatement(sql02);

        preparedStatement01.setString(1, u.getUserName());
        preparedStatement01.setString(2, u.getPassword());

        preparedStatement02.setString(1, u.getUserName());

        int num1 = preparedStatement01.executeUpdate();
        int num2 = preparedStatement02.executeUpdate();


        if (num1 > 0 && num2 > 0) {
            connection.commit();
//            System.out.println("成功");
            return true;
        } else {
            connection.rollback();
//            System.out.println("失败");
            return false;
        }
    }

    public boolean InsertArticle(Article article) throws IOException, ClassNotFoundException, SQLException {
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
        String sql01 = "insert into test_article  (article_name, article_writer, article_introduce, article_type) values (?,?,?,?)";
        String sql02 = "insert into test_article_detail values (?,?)";


        //开启事务，保证两次插入都能操作成功
        connection.setAutoCommit(false);

        PreparedStatement preparedStatement01 = connection.prepareStatement(sql01, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement preparedStatement02 = connection.prepareStatement(sql02);

        preparedStatement01.setString(1, article.getName());
        preparedStatement01.setString(2, article.getWriter());
        preparedStatement01.setString(3, article.getIntroduce());
        preparedStatement01.setString(4, article.getType());


        int num1 = preparedStatement01.executeUpdate();
        if (num1 > 0) {
//            System.out.println("第一步成功");
            ResultSet resultSet = preparedStatement01.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
//                System.out.println(id);
                preparedStatement02.setInt(1, id);
                preparedStatement02.setString(2, article.getDetail());
                int num2 = preparedStatement02.executeUpdate();
                if (num2 > 0) {
                    connection.commit();
//                    System.out.println("第二步成功");
                    return true;
                } else {
                    connection.rollback();
//                    System.out.println("第二步失败");
                    return false;
                }
            }
        } else {
            connection.rollback();
//            System.out.println("第一步失败");
            return false;
        }
        return false;
    }

    public boolean InsertUserCollection(User_Article user_article) throws IOException, ClassNotFoundException, SQLException {
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
        String sql = "insert into test_user_collection values (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, user_article.getUserName());
        preparedStatement.setString(2, user_article.getId());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean InsertFollow(Follower_Followee follower_followee) throws IOException, ClassNotFoundException, SQLException {
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
        String sql = "insert into test_user_follow values (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, follower_followee.getFollower());
        preparedStatement.setString(2, follower_followee.getFollowee());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean InsertMultipleArticles(List<Article> articles) throws IOException, ClassNotFoundException, SQLException {
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
        String sql01 = "insert into test_article  (article_name, article_writer, article_introduce, article_type) values (?,?,?,?)";
        String sql02 = "insert into test_article_detail values (?,?)";


        //开启事务，保证两次插入都能操作成功
        connection.setAutoCommit(false);

        boolean flag = true;

        for (int i = 0; i < articles.size(); i++) {
            PreparedStatement preparedStatement01 = connection.prepareStatement(sql01, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement02 = connection.prepareStatement(sql02);

            preparedStatement01.setString(1, articles.get(i).getName());
            preparedStatement01.setString(2, articles.get(i).getWriter());
            preparedStatement01.setString(3, articles.get(i).getIntroduce());
            preparedStatement01.setString(4, articles.get(i).getType());

            if (preparedStatement01.executeUpdate() > 0) {
                ResultSet resultSet = preparedStatement01.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    preparedStatement02.setInt(1, id);
                    preparedStatement02.setString(2, articles.get(i).getDetail());
                    if (preparedStatement02.executeUpdate() > 0) {
                        connection.commit();
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
            }else {
                connection.rollback();
                return false;
            }
        }
        return flag;
    }



}

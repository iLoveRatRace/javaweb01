package com.example.dao;

import com.example.pojo.*;
import pojo.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * @author xiaojin
 * @version 1.0
 */
public class Select {
    public boolean selectByInfo(User u) throws IOException, ClassNotFoundException, SQLException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //定义sql语句
        String sql = "select * from test_user where db_userName =? and db_password =?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, u.getUserName());
        preparedStatement.setString(2, u.getPassword());

        ResultSet resultSet = preparedStatement.executeQuery();

        //判断是否查询到结果
        return resultSet.next();
    }

    public boolean selectByName(User u) throws IOException, ClassNotFoundException, SQLException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //定义sql语句
        String sql = "select * from test_user where db_userName =?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, u.getUserName());

        ResultSet resultSet = preparedStatement.executeQuery();

        //判断是否查询到结果
        return resultSet.next();
    }

    public User_Detail selectDetalByName(String userName) throws IOException, ClassNotFoundException, SQLException {
//        System.out.println("开始查找");

        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //定义sql语句
        String sql = "select * from test_user_detail where user_name =?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, userName);

        ResultSet resultSet = preparedStatement.executeQuery();

        User_Detail user_detail = new User_Detail();
        if (resultSet.next()) {
            user_detail.setUserName(resultSet.getString(1));
            user_detail.setSex(resultSet.getString(2));
            user_detail.setIntroduce(resultSet.getString(3));
            user_detail.setBirthday(resultSet.getString(4));
            user_detail.setAdministrator(resultSet.getString(5));
        }

//        System.out.println("查找成功");

        return user_detail;

    }

    public Article_Detail selectDetailById(String id) throws ClassNotFoundException, SQLException, IOException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "select * from test_article_detail where article_id =?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        Article_Detail article_detail = new Article_Detail();
        if (resultSet.next()) {
//            System.out.println("查到数据");
            article_detail.setId(resultSet.getString(1));
            article_detail.setDetail(resultSet.getString(2));
        }

        return article_detail;
    }

    public List<Article> selectArticleByWriter(String userName) throws ClassNotFoundException, SQLException, IOException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "select * from test_article where article_writer =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Article> articles = new ArrayList<>();
//        Arrays.toString(articles);

        while (resultSet.next()) {
//            if(i > articles.length - 1){//扩容
//                Article[] temp = articles;
//                articles = new Article[articles.length + 5];
//                //拷贝
//                System.arraycopy(temp, 0, articles, 0, temp.length);
//            }
            Article article = new Article();
            article.setId(resultSet.getString(1));
            article.setName(resultSet.getString(2));
            article.setWriter(resultSet.getString(3));
            article.setIntroduce(resultSet.getString(4));
            article.setType(resultSet.getString(5));
            article.setCreateTime(resultSet.getString(6));
            article.setUpdateTime(resultSet.getString(7));
            articles.add(article);
        }

        return articles;
    }

    public List<Article> selectCollectionByUserName(String userName) throws ClassNotFoundException, SQLException, IOException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql01 = "select * from test_user_collection where userName =?";
        PreparedStatement preparedStatement01 = connection.prepareStatement(sql01);
        preparedStatement01.setString(1, userName);
        ResultSet resultSet01 = preparedStatement01.executeQuery();
        List<String> idList = new ArrayList<>();
        while (resultSet01.next()) {
            //将查询到的文章的id加入列表中
            String string = resultSet01.getString(2);
//            System.out.println(string);
            idList.add(string);
        }

        List<Article> articles = new ArrayList<>();
        int i = 0;
        while (i < idList.size()) {//根据id逐个进行查询
//            System.out.println("开始查询");

            String sql02 = "select * from test_article where article_id =?";
            PreparedStatement preparedStatement02 = connection.prepareStatement(sql02);

//            System.out.println(idList.get(i));

            preparedStatement02.setString(1, idList.get(i));
            ResultSet resultSet02 = preparedStatement02.executeQuery();

//            System.out.println("开始将结果添加");
            while (resultSet02.next()) {//将搜索到的结果添加到集合中
                Article article = new Article();
                article.setId(resultSet02.getString(1));
                article.setName(resultSet02.getString(2));
                article.setWriter(resultSet02.getString(3));
                article.setIntroduce(resultSet02.getString(4));
                article.setType(resultSet02.getString(5));
                article.setCreateTime(resultSet02.getString(6));
                article.setUpdateTime(resultSet02.getString(7));

//                System.out.println(article);
                articles.add(article);
            }
            i++;
        }
        return articles;
    }

    public boolean selectCollectedOrNot(User_Article user_article) throws IOException, ClassNotFoundException, SQLException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //定义sql语句
        String sql = "select * from test_user_collection where userName =? and article_id =?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, user_article.getUserName());
        preparedStatement.setString(2, user_article.getId());


        ResultSet resultSet = preparedStatement.executeQuery();

        //判断是否查询到结果
        return resultSet.next();
    }

    public List<User_Detail> selectFolloweeByFollower(String userName) throws ClassNotFoundException, SQLException, IOException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql01 = "select * from test_user_follow where follower_name =?";
        PreparedStatement preparedStatement01 = connection.prepareStatement(sql01);
        preparedStatement01.setString(1, userName);
        ResultSet resultSet01 = preparedStatement01.executeQuery();
        List<String> userNameList = new ArrayList<>();
        while (resultSet01.next()) {
            //将查询到的用户的用户名加入列表中
            String string = resultSet01.getString(2);
//            System.out.println(string);
            userNameList.add(string);
        }

        List<User_Detail> user_details = new ArrayList<>();
        int i = 0;
        while (i < userNameList.size()) {//根据用户名逐个进行查询

            String sql02 = "select * from test_user_detail where user_name =?";
            PreparedStatement preparedStatement02 = connection.prepareStatement(sql02);

            preparedStatement02.setString(1, userNameList.get(i));
            ResultSet resultSet02 = preparedStatement02.executeQuery();

            while (resultSet02.next()) {//将搜索到的结果添加到集合中
                User_Detail user_detail = new User_Detail();
                user_detail.setUserName(resultSet02.getString(1));
                user_detail.setSex(resultSet02.getString(2));
                user_detail.setIntroduce(resultSet02.getString(3));
                user_detail.setBirthday(resultSet02.getString(4));
                user_detail.setAdministrator(resultSet02.getString(5));

                user_details.add(user_detail);
            }
            i++;
        }
        return user_details;
    }

    public List<User_Detail> selectUserFaintly(String info) throws ClassNotFoundException, SQLException, IOException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "select * from test_user_detail " +
                "where user_name like ?" +
                "or sex like ?" +
                "or introduce like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 1; i < 4; i++) {
            preparedStatement.setString(i, "%" + info + "%");
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User_Detail> userList = new ArrayList<>();
        while (resultSet.next()) {
            //将查询到的用户加入列表中
            User_Detail user_detail = new User_Detail();
            user_detail.setUserName(resultSet.getString(1));
            user_detail.setSex(resultSet.getString(2));
            user_detail.setIntroduce(resultSet.getString(3));
            user_detail.setBirthday(resultSet.getString(4));
            user_detail.setAdministrator(resultSet.getString(5));

            userList.add(user_detail);
        }

        return userList;
    }

    public boolean selectFollowedOrNot(Follower_Followee follower_followee) throws IOException, ClassNotFoundException, SQLException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //定义sql语句
        String sql = "select * from test_user_follow where follower_name =? and followee_name =?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, follower_followee.getFollower());
        preparedStatement.setString(2, follower_followee.getFollowee());


        ResultSet resultSet = preparedStatement.executeQuery();

        //判断是否查询到结果
        return resultSet.next();
    }

    public List<Article> selectArticleFaintly(String info) throws ClassNotFoundException, SQLException, IOException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql01 = "select * from test_article where " +
                "article_name like ?" +
                "or article_writer like ?" +
                "or article_introduce like ?" +
                "or article_type like ?";
        String sql02 = "select * from test_article_detail where article_detail like ?";
        PreparedStatement preparedStatement01 = connection.prepareStatement(sql01);
        PreparedStatement preparedStatement02 = connection.prepareStatement(sql02);
        for (int i = 1; i <= 4; i++) {
            preparedStatement01.setString(i, "%" + info + "%");
        }
        preparedStatement02.setString(1, "%" + info + "%");

        ResultSet resultSet01 = preparedStatement01.executeQuery();
        ResultSet resultSet02 = preparedStatement02.executeQuery();
        Set<String> idSet = new HashSet<>();
        //使用Set集合：搜索文章简介表和文章详情表两张表后
        //搜索的结果可能会有重复，Set接口不能存放重复的元素
        while (resultSet01.next()) {
            //将查询到的文章的id加入列表中
            String string = resultSet01.getString(1);
            idSet.add(string);
        }
        while (resultSet02.next()) {
            //将查询到的文章的id加入列表中
            String string = resultSet02.getString(1);
            idSet.add(string);
        }

        List<Article> articles = new ArrayList<>();

        for (String s : idSet) {
            String sql03 = "select * from test_article where article_id =?";
            PreparedStatement preparedStatement03 = connection.prepareStatement(sql03);

            preparedStatement03.setString(1, s);
            ResultSet resultSet03 = preparedStatement03.executeQuery();

            while (resultSet03.next()) {//将搜索到的结果添加到集合中
                Article article = new Article();
                article.setId(resultSet03.getString(1));
                article.setName(resultSet03.getString(2));
                article.setWriter(resultSet03.getString(3));
                article.setIntroduce(resultSet03.getString(4));
                article.setType(resultSet03.getString(5));
                article.setCreateTime(resultSet03.getString(6));
                article.setUpdateTime(resultSet03.getString(7));

                articles.add(article);
            }
        }

        return articles;
    }

    public List<User_Detail> selectAllUsers() throws ClassNotFoundException, SQLException, IOException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "select * from test_user_detail";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<User_Detail> userList = new ArrayList<>();
        while (resultSet.next()) {
            //将查询到的用户加入列表中
            User_Detail user_detail = new User_Detail();
            user_detail.setUserName(resultSet.getString(1));
            user_detail.setSex(resultSet.getString(2));
            user_detail.setIntroduce(resultSet.getString(3));
            user_detail.setBirthday(resultSet.getString(4));
            user_detail.setAdministrator(resultSet.getString(5));

            userList.add(user_detail);
        }

        return userList;
    }

    public List<Article> selectAllArticles() throws ClassNotFoundException, SQLException, IOException {
        //获取连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/dao/jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "select * from test_article";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Article> articleList = new ArrayList<>();
        while (resultSet.next()) {
            Article article = new Article();
            article.setId(resultSet.getString(1));
            article.setName(resultSet.getString(2));
            article.setWriter(resultSet.getString(3));
            article.setIntroduce(resultSet.getString(4));
            article.setType(resultSet.getString(5));
            article.setCreateTime(resultSet.getString(6));
            article.setUpdateTime(resultSet.getString(7));

            articleList.add(article);
        }

        return articleList;
    }

}

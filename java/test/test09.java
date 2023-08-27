package test;

import com.example.dao.Insert;
import com.example.pojo.User_Article;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaojin
 * @version 1.0
 */
public class test09 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        User_Article user_article = new User_Article();
        user_article.setUserName("xiaoJin");
        user_article.setId("1");

        boolean b = new Insert().InsertUserCollection(user_article);
        System.out.println(b);
    }
}

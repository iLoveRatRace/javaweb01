package test;

import com.example.dao.Select;
import com.example.pojo.Article;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xiaojin
 * @version 1.0
 */
public class test10 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        List<Article> articles = new Select().selectCollectionByUserName("xiaoJin");
        System.out.println(articles);
    }
}

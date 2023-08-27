package test;

import com.example.dao.Insert;
import com.example.pojo.Article;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaojin
 * @version 1.0
 */
public class test08 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Article article = new Article();
        article.setName("说明文01");
        article.setType("说明文");
        article.setIntroduce("这是第一篇说明文");
        article.setWriter("buygay");
        article.setDetail("123123123");


        Insert insert = new Insert();
        boolean b = insert.InsertArticle(article);

        if (b){
            System.out.println("chenggong");
        }else {
            System.out.println("shibai");
        }

    }
}

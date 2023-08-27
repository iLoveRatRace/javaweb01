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
public class test07 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Select select = new Select();
        List<Article> articles = select.selectArticleByWriter("buygay");
        for(Article article: articles){
            System.out.println(article);
        }
    }
}

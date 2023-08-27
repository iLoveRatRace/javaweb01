package test;

import com.example.dao.Select;
import com.example.pojo.Article_Detail;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaojin
 * @version 1.0
 */
public class test06 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Select select = new Select();
        Article_Detail article_detail = select.selectDetailById("1");
        System.out.println(article_detail);
    }
}

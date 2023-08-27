package test;

import com.example.dao.Select;
import com.example.pojo.User_Detail;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaojin
 * @version 1.0
 */
public class test05 {
    public static void main(String[] args) {
        Select select = new Select();
        User_Detail user_detail;
        try {
            user_detail = select.selectDetalByName("xiaoJin");
        } catch (ClassNotFoundException | IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(user_detail);
    }
}

package test;

import com.example.dao.Insert;
import com.example.pojo.User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaojin
 * @version 1.0
 */
public class test03 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        User user = new User();
        user.setUserName("buygay");
        user.setPassword("123");

        Insert insert = new Insert();
        boolean b = insert.InsertUser(user);
        System.out.println(b);

    }
}

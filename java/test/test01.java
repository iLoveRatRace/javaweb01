package test;

import com.example.dao.Select;
import com.example.pojo.User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaojin
 * @version 1.0
 */
public class test01 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        User user = new User();
        user.setUserName("buygay");
        user.setPassword("123");
        Select select = new Select();
        boolean b = select.selectByInfo(user);
        System.out.println(b);
    }
}

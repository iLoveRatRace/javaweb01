package test;

import com.example.dao.Select;
import com.example.pojo.User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaojin
 * @version 1.0
 */
public class test02 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        String name = "buygay";
        User user = new User();
        user.setUserName(name);
        Select select = new Select();
        boolean b = select.selectByName(user);
        System.out.println(b);
    }
}

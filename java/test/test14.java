package test;

import com.example.dao.Select;
import com.example.pojo.User_Detail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xiaojin
 * @version 1.0
 */
public class test14 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        List<User_Detail> user_details = new Select().selectUserFaintly("男");
        System.out.println(user_details);
    }
}

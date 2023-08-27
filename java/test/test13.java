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
public class test13 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        List<User_Detail> xiaoJin = new Select().selectFolloweeByFollower("xiaoJin");
        System.out.println(xiaoJin);
    }
}

package test;

import com.example.dao.Insert;
import com.example.pojo.Follower_Followee;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaojin
 * @version 1.0
 */
public class test15 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Follower_Followee follower_followee = new Follower_Followee();
        follower_followee.setFollower("buygay");
        follower_followee.setFollowee("xiaoXin");

        boolean b = new Insert().InsertFollow(follower_followee);
        System.out.println(b);
    }
}

package test;

import com.example.dao.Delete;
import com.example.pojo.Follower_Followee;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaojin
 * @version 1.0
 */
public class test17 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Follower_Followee follower_followee = new Follower_Followee();
        follower_followee.setFollower("xiaoJin");
        follower_followee.setFollowee("buygay");

        boolean b = new Delete().deleteFollow(follower_followee);
        System.out.println(b);
    }
}

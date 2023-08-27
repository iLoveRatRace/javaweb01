package test;

import com.example.dao.Update;
import com.example.pojo.cP_User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaojin
 * @version 1.0
 * test updatePassword
 */
public class test04 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
//        User user = new User();
//        user.setUserName("jin");
//        user.setPassword("123");
//        String newPsw = "1234";
        cP_User cP_user = new cP_User();
        cP_user.setUserName("jin");
        cP_user.setOld_password("1234");
        cP_user.setNew_password("123");

        Update update = new Update();
        boolean b = update.updatePassword(cP_user);
        if(b){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }

    }
}

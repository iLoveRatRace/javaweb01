package com.example.Controller;

import com.alibaba.fastjson.JSON;
import com.example.dao.Select;
import com.example.pojo.User;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().readLine();
        User user = JSON.parseObject(json, User.class);
//        System.out.println(user);

        Select select = new Select();
        boolean flag;
        try {
            flag = select.selectByInfo(user);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        if(flag){
//            System.out.println(user.getUserName());
            response.getWriter().write(user.getUserName());
        }else {
            response.getWriter().print(false);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }
}

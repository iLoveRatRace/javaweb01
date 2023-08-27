package com.example.Controller; /**
 * @author xiaojin
 * @version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.example.dao.Update;
import com.example.pojo.cP_User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/changePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getReader().readLine();
        cP_User cP_user = JSON.parseObject(json, cP_User.class);
        System.out.println(cP_user);

        Update update = new Update();
        boolean flag;
        try {
            flag = update.updatePassword(cP_user);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(flag);
        response.getWriter().print(flag);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

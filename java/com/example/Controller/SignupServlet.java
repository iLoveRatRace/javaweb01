package com.example.Controller;

import com.alibaba.fastjson.JSON;
import com.example.dao.Insert;
import com.example.pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signupServlet")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().readLine();
        User user = JSON.parseObject(json, User.class);
//        System.out.println(user);

        boolean b;
        try {
            b = new Insert().InsertUser(user);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().print(b);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

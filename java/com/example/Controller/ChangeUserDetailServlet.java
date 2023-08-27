package com.example.Controller; /**
 * @author xiaojin
 * @version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.example.dao.Update;
import com.example.pojo.User_Detail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet("/changeUserDetailServlet")
public class ChangeUserDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getReader().readLine();
        json = new String(json.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        User_Detail user_detail = JSON.parseObject(json, User_Detail.class);

//        System.out.println("1" + user_detail);

        Update update = new Update();
        boolean b;
        try {
            b = update.updateUserDetail(user_detail);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

//        System.out.println(b);
        response.getWriter().print(b);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

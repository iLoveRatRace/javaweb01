package com.example.Controller; /**
 * @author xiaojin
 * @version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.example.dao.Select;
import com.example.pojo.User_Detail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/searchUsersFaintlyServlet")
public class SearchUsersFaintlyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String info = request.getReader().readLine();
//        System.out.println(info);

        Select select = new Select();

        List<User_Detail> user_details;
        try {
            user_details = select.selectUserFaintly(info);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

//        System.out.println(user_details);
        String s = JSON.toJSONString(user_details);
//        System.out.println(s);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

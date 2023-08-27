package com.example.Controller;

import com.alibaba.fastjson.JSON;
import com.example.dao.Select;
import com.example.pojo.User_Detail;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/personalPageServlet")
public class PersonalPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getReader().readLine();
//        System.out.println(userName);

        //在个人中心页面加载完成后在数据库中查找该用户的详细信息
        Select select = new Select();
        User_Detail user_detail;
        try {
            user_detail = select.selectDetalByName(userName);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(user_detail);

        response.setContentType("text/json;charset=utf-8");
//        System.out.println(JSON.toJSONString(user_detail));
        response.getWriter().print(JSON.toJSONString(user_detail));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }
}

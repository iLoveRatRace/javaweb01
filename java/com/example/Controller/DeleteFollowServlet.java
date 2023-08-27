package com.example.Controller; /**
 * @author xiaojin
 * @version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.example.dao.Delete;
import com.example.pojo.Follower_Followee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteFollowServlet")
public class DeleteFollowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getReader().readLine();
        Follower_Followee follower_followee = JSON.parseObject(json, Follower_Followee.class);

        boolean b;
        try {
            b = new Delete().deleteFollow(follower_followee);
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

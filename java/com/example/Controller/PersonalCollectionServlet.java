package com.example.Controller; /**
 * @author xiaojin
 * @version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.example.dao.Select;
import com.example.pojo.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/personalCollectionServlet")
public class PersonalCollectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getReader().readLine();
//        System.out.println(userName);

        Select select = new Select();

        List<Article> articles;
        try {
            articles = select.selectCollectionByUserName(userName);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String s = JSON.toJSONString(articles);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

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

@WebServlet("/personalPublishServlet")
public class PersonalPublishServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getReader().readLine();

        Select select = new Select();

//        System.out.println("开始查找");
        List<Article> articles;
        try {
            articles = select.selectArticleByWriter(userName);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

//        System.out.println("查找结束");
        String s = JSON.toJSONString(articles);
//        System.out.println(s);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

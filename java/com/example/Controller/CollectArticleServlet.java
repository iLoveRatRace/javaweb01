package com.example.Controller; /**
 * @author xiaojin
 * @version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.example.dao.Insert;
import com.example.pojo.User_Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet("/collectArticleServlet")
public class CollectArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getReader().readLine();
        json = new String(json.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        User_Article user_article = JSON.parseObject(json, User_Article.class);
//        System.out.println(user_article);

        Insert insert = new Insert();
        boolean b;
        try {
            b = insert.InsertUserCollection(user_article);
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

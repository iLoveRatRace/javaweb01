package com.example.Controller; /**
 * @author xiaojin
 * @version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.example.dao.Update;
import com.example.pojo.Article_Detail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet("/changeArticleDetailServlet")
public class ChangeArticleDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getReader().readLine();
        json = new String(json.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        Article_Detail article_detail = JSON.parseObject(json, Article_Detail.class);
        System.out.println(article_detail);

        boolean b;
        try {
            b = new Update().updatArticleDetail(article_detail);
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

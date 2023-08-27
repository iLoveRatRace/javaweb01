package com.example.Controller; /**
 * @author xiaojin
 * @version 1.0
 */

import com.example.dao.Select;
import com.example.pojo.Article_Detail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/articleDetailServlet")
public class ArticleDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getReader().readLine();
        
        Article_Detail article_detail;
        try {
            article_detail = new Select().selectDetailById(id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

//        System.out.println(article_detail);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().print(article_detail.getDetail());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

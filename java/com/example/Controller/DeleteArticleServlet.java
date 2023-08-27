package com.example.Controller; /**
 * @author xiaojin
 * @version 1.0
 */

import com.example.dao.Delete;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
///v1/admin/article/:id
@WebServlet("/deleteArticleServlet")
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getReader().readLine();

        boolean b;
        try {
            b = new Delete().deleteArticle(id);
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

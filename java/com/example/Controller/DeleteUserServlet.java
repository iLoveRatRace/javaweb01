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

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getReader().readLine();
//        System.out.println(userName);

        boolean b;
        try {
            b = new Delete().deleteUser(userName);
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

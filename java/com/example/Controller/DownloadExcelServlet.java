package com.example.Controller; /**
 * @author xiaojin
 * @version 1.0
 */

import com.example.Service.ExcelService;
import com.example.dao.Select;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/downloadExcelServlet")
public class DownloadExcelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean b;
        try {
            b = ExcelService.downloadExcel(new Select().selectAllArticles());
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

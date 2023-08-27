package com.example.Controller; /**
 * @author xiaojin
 * @version 1.0
 */

import com.example.Service.ExcelService;
import com.example.dao.Insert;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.example.pojo.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/uploadExcelServlet")
@MultipartConfig
public class UploadExcelServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = request.getParameter("fileName");
        InputStream fileContent = filePart.getInputStream();

        // Save the file to the server


        File file = new File(getServletContext().getRealPath("/") + "uploadExcelServlet\\" + fileName);
//        System.out.println(getServletContext().getRealPath("/") + "uploadExcelServlet\\" + fileName);

        //在创建文件的时候，避免其文件所在的文件夹不存在，然后抛出异常
        file.getParentFile().mkdirs();
        try (OutputStream out = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        List<Article> articles;
        try {
            articles = ExcelService.uploadExcel(file);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }

//        System.out.println(articles);

        boolean b;
        try {
            b = new Insert().InsertMultipleArticles(articles);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/json;charset=utf-8");
        if(b){
            response.getWriter().write("上传成功");
        }else {
            response.getWriter().write("上传失败");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

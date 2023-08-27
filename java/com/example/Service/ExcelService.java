package com.example.Service;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import com.example.pojo.Article;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaojin
 * @version 1.0
 */
public class ExcelService {
    //下载文件
    public static boolean downloadExcel(List<Article> list) {
        //第一步：创建一个workbook对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二步：在workbook中创建一个sheet对应Excel中的sheet
        HSSFSheet sheet = workbook.createSheet("用户表一");
        //第三步：在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步：创建单元格，设置表头
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("文章ID");
        cell = row.createCell((short) 1);
        cell.setCellValue("文章名称");
        cell = row.createCell((short) 2);
        cell.setCellValue("作者");
        cell = row.createCell((short) 3);
        cell.setCellValue("介绍");
        cell = row.createCell((short) 4);
        cell.setCellValue("类别");
        cell = row.createCell((short) 5);
        cell.setCellValue("创建时间");
        cell = row.createCell((short) 6);
        cell.setCellValue("更新时间");

        //第五步：写入实体数据，实际应用中这些 数据从数据库得到，对象封装数据，集合包对象。对象的属性值对应表的每行的值
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            Article article = list.get(i);
            //创建单元格设值
            row1.createCell((short) 0).setCellValue(article.getId());
            row1.createCell((short) 1).setCellValue(article.getName());
            row1.createCell((short) 2).setCellValue(article.getWriter());
            row1.createCell((short) 3).setCellValue(article.getIntroduce());
            row1.createCell((short) 4).setCellValue(article.getType());
            row1.createCell((short) 5).setCellValue(article.getCreateTime());
            row1.createCell((short) 6).setCellValue(article.getUpdateTime());
        }
        //将文件保存到指定的位置
        try {
            FileOutputStream fos = new FileOutputStream("D:\\RatRace\\sys\\article.xlsx");
            workbook.write(fos);
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //上传文件
    public static List<Article> uploadExcel(File xlsxFile) throws IOException, InvalidFormatException {
        // 获得工作簿
        Workbook workbook = WorkbookFactory.create(xlsxFile);

        List<Article> articles = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        // 获得行数
        int rows = sheet.getLastRowNum() + 1;


        // 读取数据
        for (int row = 1; row < rows; row++) {//按行读取，从第二行开始，即不读取表头
            Row r = sheet.getRow(row);
            Article article = new Article();

            //在读取excel单元格数据转化之前设置单元格类型为String
            r.getCell(0).setCellType(CellType.STRING);
            r.getCell(1).setCellType(CellType.STRING);
            r.getCell(2).setCellType(CellType.STRING);
            r.getCell(3).setCellType(CellType.STRING);
            r.getCell(4).setCellType(CellType.STRING);

            article.setName(r.getCell(0).getStringCellValue());
            article.setWriter(r.getCell(1).getStringCellValue());
            article.setIntroduce(r.getCell(2).getStringCellValue());
            article.setType(r.getCell(3).getStringCellValue());
            article.setDetail(r.getCell(4).getStringCellValue());

            articles.add(article);
        }
        return articles;
    }



}




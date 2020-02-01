package com.fancy.common.controller;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

/**
 * @author Joah
 * @time 2020/1/31 23:07
 */


public class PoiController {

    @Test
    public void readExcel03() throws FileNotFoundException {

        InputStream inputStream = new FileInputStream("");


    }

    @Test
    public void writeExcel03() throws FileNotFoundException {

        // 1、创建workbook 2003版本
        Workbook workbook = new HSSFWorkbook();

        // 2、根据workbook创建Sheet
        Sheet sheet = workbook.createSheet("讲师列表");

        // 3、根据sheet创建 row
        Row row = sheet.createRow(0);

        // 4、根据行创建cell
        Cell cell = row.createCell(0);

        // 5、向 cell中写入值
        cell.setCellValue("张三");

        // 6、使用输出流写入到文件中
        OutputStream outputStream = new FileOutputStream("D:\\excel\\03.xls");

        // 7、把 workbook 通过输出流写入到文件
        try {
            workbook.write(outputStream);
            // 8、关闭流
            outputStream.close();
        } catch (IOException e) {

        }
    }

    @Test
    public void writeExcel07() throws FileNotFoundException {

        // 1、创建workbook 2003版本
        Workbook workbook = new XSSFWorkbook();

        // 2、根据workbook创建Sheet
        Sheet sheet = workbook.createSheet("讲师列表");

        // 3、根据sheet创建 row
        Row row = sheet.createRow(0);

        // 4、根据行创建cell
        Cell cell = row.createCell(0);

        // 5、向 cell中写入值
        cell.setCellValue("张三");

        // 6、使用输出流写入到文件中
        OutputStream outputStream = new FileOutputStream("D:\\excel\\07.xlsx");

        // 7、把 workbook 通过输出流写入到文件
        try {
            workbook.write(outputStream);
            // 8、关闭流
            outputStream.close();
        } catch (IOException e) {

        }

    }
}

package com.cedar.worklog.util;

import com.cedar.worklog.entity.DayTableWork;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.List;

public class UseExcel {

    //创建Excel文件
    public void createExcel(String path) throws Exception {
        //创建Excel文件对象
        Workbook wb = new XSSFWorkbook();
        //用文件对象创建sheet对象
        XSSFSheet sheet = ((XSSFWorkbook) wb).createSheet();
        Sheet sheet2 = wb.createSheet("sheet1");
        //用sheet对象创建行对象
        XSSFRow row = sheet.createRow(0);
        //创建单元格样式
        XSSFCellStyle cellStyle = ((XSSFWorkbook) wb).createCellStyle();
        //用行对象创建单元格对象Cell
        XSSFCell cell = row.createCell(0);
        //用cell对象读写。设置Excel工作表的值
        cell.setCellValue(1);
        //输出Excel文件
        FileOutputStream output = new FileOutputStream(path);
        wb.write(output);
        //output.flush();
        output.close();
    }

    //创建excel的表头,设置字体及字体颜色，设置单元格填充色
    public void createExcelTop (String path,List<DayTableWork> todayworks,List<DayTableWork> tomworks) throws Exception{
        //创建Excel对象
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //创建sheet对象
        Sheet sheet = xssfWorkbook.createSheet();
        int rownum = 1;
        //创建row
        Row row = sheet.createRow(rownum);
        rownum ++;


        //Excel样式 index
        XSSFCellStyle indexstyle = xssfWorkbook.createCellStyle();
        XSSFFont indexfont = xssfWorkbook.createFont();
        setFont(indexfont,"宋体",12,true);
        setStyle(indexstyle,indexfont);

        //Excel样式 data
        XSSFCellStyle nomstyle = xssfWorkbook.createCellStyle();
        XSSFFont datafont = xssfWorkbook.createFont();
        setFont(datafont,"仿宋GB2312",12,false);
        setStyle(nomstyle,datafont);

        //展开竖向居中
        XSSFCellStyle kstyle = xssfWorkbook.createCellStyle();
        setStyle(kstyle,datafont);

        //创建cell并设置cell的值
        row.createCell(0).setCellValue("工作开展");
        row.createCell(1).setCellValue("序号");
        row.createCell(2).setCellValue("工作类型");
        row.createCell(3).setCellValue("主要内容");
        row.createCell(4).setCellValue("基本依据");
        row.createCell(5).setCellValue("时间安排");
        row.createCell(6).setCellValue("负责人");
        row.createCell(7).setCellValue("体现方式");
        row.createCell(8).setCellValue("备注");
        row.setHeight((short) (15.625 * 54));
        sheet.setColumnWidth((short) 3, (short) (35.7 * 100));

        for(int i = 0;i<=8;i++){
            row.getCell(i).setCellStyle(indexstyle);
        }

        //插入数据
        for(DayTableWork todaywork : todayworks){
            Row row2 = sheet.createRow(rownum);
            rownum ++;
            setMyRow("今\n日\n完\n成\n工\n作",row2,todaywork);

            for(int i = 0;i<=8;i++){
                row2.getCell(i).setCellStyle(nomstyle);
            }
            row2.setHeight((short) (15.625 * 54));
        }
        int temp = rownum - 1;
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(2,temp,0,0));
        //sheet.getRow(2).getCell(0).getCellStyle().setRotation((short)255);

        for(DayTableWork tomwork : tomworks){
            Row row3 = sheet.createRow(rownum);
            rownum ++;
            setMyRow("明\n日\n工\n作\n计\n划",row3,tomwork);

            for(int i = 0;i<=8;i++){
                row3.getCell(i).setCellStyle(nomstyle);
            }
            row3.setHeight((short) (15.625 * 54));
        }

        if(temp + 1 < rownum - 1) {
            sheet.addMergedRegion(new CellRangeAddress(temp + 1, rownum - 1, 0, 0));
                //sheet.getRow(temp + 1).getCell(0).getCellStyle().setRotation((short) 255);
        }

        //Excel样式 Top
        XSSFCellStyle Topstyle = xssfWorkbook.createCellStyle();
        Topstyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Topstyle.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont topfont = xssfWorkbook.createFont();
        setFont(topfont,"方正小标简体宋",16,true);
        Topstyle.setFont(topfont);

        //设置标题，以及设置单元格的样式，有些样式只对单元格有效
        Row row5 = sheet.createRow(0);
        row5.setHeight((short) (15.625 * 54));
        Cell cell1 = row5.createCell(0);
        cell1.setCellValue("日工作计划表");
        cell1.setCellStyle(Topstyle);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,8));
        //row5.createCell(3).setCellValue();

        //输出Excel对象
        FileOutputStream output = new FileOutputStream(path);
        xssfWorkbook.write(output);
        output.flush();
        output.close();
    }

    public void setFont(XSSFFont font,String fontname,int fontsize,boolean bold){
        font.setFontName(fontname);
        font.setFontHeight(fontsize);
        font.setBold(bold);
        font.setColor(Font.COLOR_NORMAL);//黑色
    }

    public void setStyle(XSSFCellStyle style,XSSFFont font){
        style.setAlignment(HorizontalAlignment.CENTER);//横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        style.setFont(font);
    }

    void setMyRow(String tab,Row row,DayTableWork dtw){
        row.createCell(0).setCellValue(tab);
        row.createCell(1).setCellValue(dtw.getD_num());
        if(dtw.getD_type() == 0)
            row.createCell(2).setCellValue("上级通知");
        else
            row.createCell(2).setCellValue("本级展开");
        row.createCell(3).setCellValue(dtw.getD_content());
        row.createCell(4).setCellValue(dtw.getD_basis());
        row.createCell(5).setCellValue(dtw.getD_time());
        row.createCell(6).setCellValue(dtw.getD_principal());
        row.createCell(7).setCellValue(dtw.getD_way());
        row.createCell(8).setCellValue(dtw.getD_remarks());
    }



    public void createExcelWeek (String path,List<DayTableWork> weekworks) throws Exception{
        String table[] ={"序号","工作类型","项目","主要内容","基本依据","时间安排","负责人","体现方式","备注"};
        //创建Excel对象
        XSSFWorkbook Workbook = new XSSFWorkbook();
        //创建sheet对象
        Sheet sheet = Workbook.createSheet();
        int rownum = 0;
        //创建row
        Row row = sheet.createRow(rownum);
        rownum ++;
        row.setHeight((short) (15.625 * 54));



        XSSFCellStyle titlestyle = Workbook.createCellStyle();
        XSSFFont titlefont = Workbook.createFont();
        setFont(titlefont,"方正小标简体宋",16,true);
        titlestyle.setFont(titlefont);
        titlestyle.setAlignment(HorizontalAlignment.CENTER);
        titlestyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //sheet.getRow(0).getCell(0).setCellStyle(titlestyle);

        row.createCell(0).setCellValue("周工作计划表");
        row.getCell(0).setCellStyle(titlestyle);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,8));


        Row row1 = sheet.createRow(rownum);
        row1.setHeight((short) (15.625 * 54));
        rownum ++;

        XSSFCellStyle indexstyle = Workbook.createCellStyle();
        XSSFFont indexfont = Workbook.createFont();
        setFont(indexfont,"宋体",12,true);
        setStyle(indexstyle,indexfont);

        for(int i = 0; i <= 8;i++){
            //System.out.println(table[i]);
            row1.createCell(i).setCellValue(table[i]);
            row1.getCell(i).setCellStyle(indexstyle);
        }


        XSSFCellStyle datastyle = Workbook.createCellStyle();
        XSSFFont datafont = Workbook.createFont();
        setFont(datafont,"仿宋GB2312",12,false);
        setStyle(datastyle,datafont);
        int num= 1;
        for(DayTableWork daytablework : weekworks){
            Row row2 = sheet.createRow(rownum);
            row2.setHeight((short) (15.625 * 54));
            rownum ++;
            row2.createCell(0).setCellValue(num);
            if(daytablework.getD_type() == 0)
                row2.createCell(1).setCellValue("上级通知");
            else
                row2.createCell(1).setCellValue("本级展开");
            row2.createCell(2).setCellValue("");
            row2.createCell(3).setCellValue(daytablework.getD_content());
            row2.createCell(4).setCellValue(daytablework.getD_basis());
            row2.createCell(5).setCellValue(daytablework.getD_time());
            row2.createCell(6).setCellValue(daytablework.getD_principal());
            row2.createCell(7).setCellValue(daytablework.getD_way());
            row2.createCell(8).setCellValue(daytablework.getD_remarks());
            num ++;

            for(int i = 0;i<=8;i++){
                row2.getCell(i).setCellStyle(datastyle);
            }

        }
        //输出Excel对象
        FileOutputStream output = new FileOutputStream(path);
        Workbook.write(output);
        output.flush();
        output.close();
    }



}
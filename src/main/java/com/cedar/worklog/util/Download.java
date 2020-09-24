package com.cedar.worklog.util;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Download {
/*
    public static void main(String arge[])
    {
        GetExcelLise("白雪松");
        //System.out.println("主函数");
        System.out.println(GetExcelLise("白雪松"));
        //System.out.println(GetExcelLise("白雪松").size());
    }*/
    public static List<String> GetExcelLise(String name,String tab){
        List<String> namelist = new ArrayList();
        String basePath=System.getProperty("user.dir") + "\\excel";
        //System.out.println(basePath);
        String[] list=new File(basePath).list();
        for(String excelname : list){
            if(excelname.contains(name) && excelname.contains(tab)){
                namelist.add(excelname);
            }
        }
        return namelist;
    }

}

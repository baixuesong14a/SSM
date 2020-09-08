package com.cedar.worklog.service.impl;

import com.cedar.worklog.dao.DTableDao;
import com.cedar.worklog.entity.DayTableWork;
import com.cedar.worklog.service.DTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DTableServiceImpl implements DTableService {

    @Autowired
    DTableDao dtabledao;

    @Override
    public boolean AddDtable(DayTableWork dtw) {

        String c_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dtabledao.InsertDTable(dtw.getD_tab(),dtw.getD_num(),dtw.getD_type(),dtw.getD_content(),dtw.getD_basis(),dtw.getD_time(),
                dtw.getD_principal(),dtw.getD_way(),dtw.getD_remarks(),c_time, dtw.getUser_id());
    }

    @Override
    public boolean DelDtable(double id) {
        return dtabledao.DeleteDTableById(id);
    }

    @Override
    public DayTableWork SelDTableById(double id) {
        return dtabledao.SelectDTableById(id);
    }

    @Override
    public List<DayTableWork> SelDTableByDay(){

        String c_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return dtabledao.SelectDtableByDay(c_time);
    }

    @Override
    public List<DayTableWork> SelDTableByDaI(int userid){

        String c_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dtabledao.SelectDtableByDaI(c_time,userid);
    }

    @Override
    public List<DayTableWork> SelDtableByDIJ(int userid){
        String c_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dtabledao.SelDTableByDIJ(c_time,userid);
    }

    @Override
    public List<DayTableWork> SelDtableByDIM(int userid){
        String c_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dtabledao.SelDTableByDIM(c_time,userid);
    }

    @Override
    public int GetNumCur(int id, int num) {
        String c_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dtabledao.GetNumCur(c_time, id, num);
    }

    @Override
    public boolean UpDataNum(int userid,List<DayTableWork> daytableworks){
        //int flag = 0;
        int num = 1;
        //String c_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        for(DayTableWork dtw: daytableworks){
            if(dtabledao.UpdataNum(num,dtw.getDt_id()))
                num ++;
        }
        //System.out.println(num +"       " + daytableworks.size());
        if(num == daytableworks.size()+1)
            return true;
        return false;
    }

    @Override
    public List<DayTableWork> SelWeek(int id){
        SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        int week  = calendar.get(Calendar.DAY_OF_WEEK) -1;
        String startday = "";

        calendar.setTime(new Date());
        if(week != 0){
            calendar.add(Calendar.DATE, - week+1 );
            startday = "'"  + sj.format(calendar.getTime()) + "'";
            //System.out.println("本周一日期为：" + sj.format(calendar.getTime()));
        }else{
            calendar.add(Calendar.DATE, - 6 );
            //System.out.println("本周一日期为：" + sj.format(calendar.getTime()));
            startday = "'"  + sj.format(calendar.getTime()) + "'";
        }
        return dtabledao.SelWeek(id,startday);
    }
}

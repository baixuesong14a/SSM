package com.cedar.worklog.service;

import com.cedar.worklog.entity.DayTableWork;


import java.util.List;

public interface DTableService {

    public boolean AddDtable(DayTableWork dtw);

    public boolean AddDtableID(DayTableWork dtw);

    public boolean DelDtable(double id);

    public DayTableWork SelDTableById(double id);

    public List<DayTableWork> SelDTableByDay();

    public List<DayTableWork> SelDTableByDaI(int id);

    public List<DayTableWork> SelDtableByDIJ(int id);

    public List<DayTableWork> SelDtableByDIM(int id);

    public List<DayTableWork> SelWeek(int id);

    public int GetNumCur(int id, int num);

    public boolean UpDataNum(int userid,List<DayTableWork> daytableworks);


    //public boolean SelDtableByTime();

}

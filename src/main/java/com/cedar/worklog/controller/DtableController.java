package com.cedar.worklog.controller;

import com.cedar.worklog.entity.DayTableWork;
import com.cedar.worklog.service.DTableService;
import com.cedar.worklog.service.UserService;
import com.cedar.worklog.util.Download;
import com.cedar.worklog.util.UseExcel;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/dtable")
public class DtableController {
    private final Logger log = LoggerFactory.getLogger(DtableController.class);

    @Autowired
    DTableService dtableservice;

    @Autowired
    UserService userservice;

    @GetMapping("/todaylist")
    public String userList(Model model){
        model.addAttribute("contents",dtableservice.SelDTableByDay());
        return "dtable/todaylist";
    }

    @GetMapping("/dtableform/{id}")
    public String todayform(Model model,@PathVariable("id") int id){
        DayTableWork dtablework = new DayTableWork();
        dtablework.setDt_id(0);
        model.addAttribute("dtablework" , new DayTableWork());

        model.addAttribute("user" , userservice.GetUserById(id));
        return "dtable/dtableform";
    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public String add(@ModelAttribute DayTableWork dtablework,Model model){
        //获取当前用户ID
        int userid = dtablework.getUser_id();

        //通过id获取当前计数器
        int num = dtableservice.GetNumCur(userid,dtablework.getD_tab());

        dtablework.setD_num(num + 1);
        //修改插入序号

        //插入数据
        dtableservice.AddDtable(dtablework);
        //System.out.println(dtablework.getD_tab());

        model.addAttribute("user",userservice.GetUserById(userid));
        return "common/adddtablesucc";
    }

    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") double id,Model model){
        int userid = dtableservice.SelDTableById(id).getUser_id();
        int tab = dtableservice.SelDTableById(id).getD_tab();
        model.addAttribute("user",userservice.GetUserById(userid));

        dtableservice.DelDtable(id);
        //重新排序
        if(tab == 0){
            if(dtableservice.UpDataNum(userid,dtableservice.SelDtableByDIJ(userid)))
                return "common/deltabsucc";
        }else {
            if (dtableservice.UpDataNum(userid, dtableservice.SelDtableByDIM(userid)))
                return "common/deltabsucc";
        }
        return "common/deltabfail";
    }


    @GetMapping("list/{id}")
    public String userview(@PathVariable("id") int id , Model model){
        model.addAttribute("contents",dtableservice.SelDTableByDaI(id));

        List<DayTableWork> content = dtableservice.SelDTableByDaI(id);
        List<DayTableWork> content_today = dtableservice.SelDtableByDIJ(id);

        List<DayTableWork> content_tomorrow = dtableservice.SelDtableByDIM(id);

        model.addAttribute("contents_today",content_today);
        model.addAttribute("contents_tomorrow",content_tomorrow);

        model.addAttribute("user",userservice.GetUserById(id));
        return "dtable/userlist";
    }

    @GetMapping("excel/{id}")
    public String CreateExcel(@PathVariable("id") int id , Model model){
        Date now=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String localname=dateFormat.format(now);
        UseExcel createExcel = new UseExcel();
        String username = userservice.GetUserById(id).getUsername();

        File file = new File(System.getProperty("user.dir") + "\\excel\\");
        if(!file .exists())
            file.mkdirs();//创建目录

        //String path = System.getProperty("user.dir") + "\\src\\main\\resources\\excel\\" + "日报：" + username + localname +".xlsx";//路径可随意替换
        String path = System.getProperty("user.dir") + "\\excel\\" + "日报：" + username + localname +".xlsx";//路径可随意替换
        List<DayTableWork> content_today = dtableservice.SelDtableByDIJ(id);
        List<DayTableWork> content_tomorrow = dtableservice.SelDtableByDIM(id);

        try {
            //随意创建一个Excel
            createExcel.createExcel(path);
            //插入数据
            createExcel.createExcelTop(path,content_today,content_tomorrow);
            //读取上一行创建的Excel
            //createExcel.getExcel(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("user",userservice.GetUserById(id));
        model.addAttribute("list", Download.GetExcelLise(username,"日报"));
        return "dtable/createdaysucc";
    }

    @GetMapping("getweek/{id}")
    public String GetWeek(@PathVariable("id") int userid , Model model){
        Date now=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String localname=dateFormat.format(now);
        UseExcel createExcel = new UseExcel();

        File file = new File(System.getProperty("user.dir") + "\\excel\\");
        if(!file .exists())
            file.mkdirs();//创建目录

        String path = System.getProperty("user.dir") + "\\excel\\" + "周报：" + userservice.GetUserById(userid).getUsername() + localname +".xlsx";//路径可随意替换

        //dtableservice.SelWeek(userid);
        try {
            //随意创建一个Excel
            createExcel.createExcel(path);
            //插入数据
            createExcel.createExcelWeek(path,dtableservice.SelWeek(userid));
            //createExcel.createExcelTop(path,content_today,content_tomorrow);
            //读取上一行创建的Excel
            //createExcel.getExcel(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("user",userservice.GetUserById(userid));
        return "common/getweeksucc";
    }
}

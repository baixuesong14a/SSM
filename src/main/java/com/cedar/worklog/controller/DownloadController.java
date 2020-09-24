package com.cedar.worklog.controller;

import com.cedar.worklog.dao.UserDao;
import com.cedar.worklog.service.UserService;
import com.cedar.worklog.util.Download;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping(value = "/download")
public class DownloadController {
    @Autowired
    UserService userservice;

    @RequestMapping("/{downfile}")
    public String downloadFile(@PathVariable("downfile") String downfile, Model model, HttpServletResponse response) throws IOException {
        //System.out.println(downfile);
        try {
            //Resource resource = new ClassPathResource("E:\\Project\\demo\\src\\main\\resources\\excel\\日报：白雪松20200902091709.xlsx");
            String downUrl = System.getProperty("user.dir") + "\\excel\\" + downfile;
            System.out.println(downUrl);

            String filename = System.getProperty("user.dir") + "\\excel\\" + downfile;
            //System.out.println("filename"  + filename);
            File file = new File(filename);
            InputStream inputStream = new FileInputStream(file);
            //强制下载不打开
            response.setContentType("application/force-download");
            OutputStream out = response.getOutputStream();
            //使用URLEncoder来防止文件名乱码或者读取错误
            response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(filename, "UTF-8"));
            int b = 0;
            byte[] buffer = new byte[1000000];
            while (b != -1) {
                b = inputStream.read(buffer);
                if(b!=-1) out.write(buffer, 0, b);
            }
            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "common/downsuccess";
    }

    @RequestMapping("list/{userid}")
    public String GetList(@PathVariable("userid") int userid,Model model){

        String username = userservice.GetUserById(userid).getUsername();
        model.addAttribute("user",userservice.GetUserById(userid));
        List<String> listday  = Download.GetExcelLise(username,"日报");
        List<String> listweek  = Download.GetExcelLise(username,"周报");

        if(listday.size()>10)
            listday = listday.subList(listday.size()-10 ,listday.size());
        if(listweek.size()>10)
            listweek.subList(listweek.size()-10 ,listweek.size());

        model.addAttribute("daylist", listday);
        model.addAttribute("weeklist", listweek);
        return "download/downlist";
    }
}

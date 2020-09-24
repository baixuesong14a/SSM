package com.cedar.worklog.controller;

import com.cedar.worklog.entity.User;
import com.cedar.worklog.service.UserService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/user")
public class ThymeleafUserController {
    private final Logger log = LoggerFactory.getLogger(ThymeleafUserController.class);

    @Autowired
    private UserService userservice;

    @GetMapping("/userlist")
    public String userList(Model model){
        //打印日志
        //log.info("ThymeleafUserController userList info log start");
        model.addAttribute("contents",userservice.GetUserAll());
        return "user/userlist";
    }

    @GetMapping("/form")
    public String form(Model model){
        //log.info("ThymeleafUserController form info log start");

        model.addAttribute("user" , new User());
        return "user/form";
    }

    @GetMapping("{id}")
    public String userview(@PathVariable("id") int id , Model model){
        User user = userservice.GetUserById(id);
        model.addAttribute("user",user);
        //System.out.println("执行");
        return "user/userview";
    }


    @GetMapping(value = "edit/{id}")
    public String editForm(@PathVariable("id") int id , Model model){
        //log.info("ThymeleafUserController editForm info log start");
        User user = userservice.GetUserById(id);
        model.addAttribute("user" , user);
        return "user/form";
    }

    /*@GetMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") int id){
        userservice.deleteById(id);
        return "/common/success";
    }*/


    @RequestMapping(value="/add",method= RequestMethod.POST)
    public String add(@ModelAttribute User user){
        //System.out.println(user.toString());
        //获取当前用户数量
        System.out.println(userservice.GetUserNum());
        if(userservice.InsertUserID(userservice.GetUserNum()+ 1,user.getUsername(),user.getUserpassword(),user.getRole()))
            return "/common/addusersucc";
        return "common/adduserfail";
    }

}
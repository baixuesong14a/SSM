package com.cedar.worklog.controller;

import com.cedar.worklog.entity.User;
import com.cedar.worklog.service.UserService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/")
public class LoginController {
    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService  userservice;

    @GetMapping("/login")
    public String login(Model model){
        //打印日志
        //log.info("ThymeleafUserController userList info log start");
        model.addAttribute("user" , new User());
        return "user/login";
    }

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String add(@ModelAttribute User user,Model model){
        String name = user.getUsername();
        String password = user.getUserpassword();

        User sqluser = userservice.GetUserByName(name);

        model.addAttribute("user" , sqluser);

        //System.out.println( password );
        //System.out.println( sqluser.getUserpassword());

        if(sqluser != null){
            if(sqluser.getUserpassword().equals(password)) {
                return "user/loginsuccess" ;
            }else{
                return "user/loginfail";
            }
        }
        return "user/loginfail";
    }
}

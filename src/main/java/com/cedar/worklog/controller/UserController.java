package com.cedar.worklog.controller;

import com.cedar.worklog.entity.User;
import com.cedar.worklog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userservice;

    @RequestMapping("/getUser")
    @ResponseBody
    List<User> getUserAll(){
        return userservice.GetUserAll();
    }
}

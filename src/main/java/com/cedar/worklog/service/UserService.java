package com.cedar.worklog.service;

import com.cedar.worklog.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    public User GetUserById(int id);

    public List<User> GetUserAll();

    public boolean InsertUser(String name,String password,int role);

    public User GetUserByName(String name);


    public boolean DeleteById(int id);
}

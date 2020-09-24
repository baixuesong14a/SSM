package com.cedar.worklog.service.impl;

import com.cedar.worklog.dao.UserDao;
import com.cedar.worklog.entity.User;
import com.cedar.worklog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userdao;

    public User GetUserById(int id){
        return userdao.GetUserById(id);
    }

    public List<User> GetUserAll(){
        return userdao.GetUserAll();
    }

    public boolean InsertUser(String name,String password,int role){
        if(userdao.GetUserByName(name) !=null )
            return false;
        return userdao.InsertUser(name,password,role);
    }

    public boolean InsertUserID(int userid,String name,String password,int role){
        if(userdao.GetUserByName(name) !=null )
            return false;
        return userdao.InsertUserID(userid,name,password,role);
    }

    public boolean DeleteById(int id){
        return userdao.DeleteById(id);
    }

    public User GetUserByName(String name){
        return userdao.GetUserByName(name);
    }

    public int GetUserNum(){
        return userdao.GetUserNum();
    }
}

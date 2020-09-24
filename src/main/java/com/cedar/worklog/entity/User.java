package com.cedar.worklog.entity;

public class User {
    private int userid;
    private String username;
    private String userpassword;
    private int role;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String toString(){
        return "ID：" + userid + "\n用户名" + username + "\n密码：" + userpassword + "\n权限：" + role;
    }
}

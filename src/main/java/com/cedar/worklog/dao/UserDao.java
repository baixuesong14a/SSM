package com.cedar.worklog.dao;

import com.cedar.worklog.entity.User;
import com.cedar.worklog.service.UserService;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    //通过Id查询信息
    @Select("select * from user where userid = #{id}")
    User GetUserById(@Param("id") int id);

    //通过用户名查询信息
    @Select("select * from user where username = #{name}")
    User GetUserByName(@Param("name") String name);

    //查询所有信息
    @Select("select * from user;")
    List<User> GetUserAll();

    //新增人员信息
    @Insert("insert into user(username,userpassword,role) values(#{name},#{password},#{role})")
    boolean InsertUser(@Param("name") String name,@Param("password") String password,@Param("role") int role);

    //通过id删除人员信息
    @Delete("delete from user where id = #{id}")
    boolean DeleteById(@Param("id") int id);
}

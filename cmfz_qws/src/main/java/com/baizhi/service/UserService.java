package com.baizhi.service;

import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baizhi.entity.RegistMap;

import java.util.List;
import java.util.Map;

public interface UserService {

    //分页查所有
    Map<String,Object> queryAll(Integer page, Integer rows);

    //修改用户状态
    void modifyStatus(User user);

    //查一
    User queryById(String id);

    //添加用户
    String add(User user);

    //修改头像
    void modifyProfile(User user);

    //登录
    Object login(String phone, String password);

    //注册
    Object regist(User user);

    /*
    * 注册月份统计
    * */
    List<Month> slelctMonth();

    /*
    *地图注册分布
    * */
    List<RegistMap> slelctMap(String gender);

    /*
    * 查所有用户
    * */
    List<User> findAlls();
}

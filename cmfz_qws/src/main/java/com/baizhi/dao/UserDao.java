package com.baizhi.dao;

import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baizhi.entity.RegistMap;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface UserDao extends BaseDao<User>{
    //修改用户状态
    void updateStatus(User user);
    //修改头像
    void updateProfile(User user);

    //登录
    User selectByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);

    //根据手机号查
    User selectByPhone(@Param("phone") String phone);

    //月份注册统计
    List<Month> slelctMonth();

    //地图注册分布
    List<RegistMap> slelctMap(@Param("gender") String gender);

    List<User> selectAlls();
}

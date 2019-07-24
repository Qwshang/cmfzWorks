package com.baizhi.service;

import com.alibaba.fastjson.JSON;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baizhi.entity.RegistMap;
import com.baizhi.util.MD5Utils;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAlls() {
        List<User> users = userDao.selectAlls();
        return users;
    }

    @Override
    public List<RegistMap> slelctMap(String gender) {
        List<RegistMap> months = userDao.slelctMap(gender);
        return months;
    }

    @Override
    public List<Month> slelctMonth() {
        List<Month> months = userDao.slelctMonth();
        return months;
    }

    @Override
    public Object regist(User user) {
        User user2 = userDao.selectByPhone(user.getPhone());
        if(user2==null){
            String s = UUID.randomUUID().toString();
            user.setId(s);
            user.setRegistTime(new Date());
            String salt = MD5Utils.getSalt();
            user.setSalt(salt);
            String password = MD5Utils.getPassword(user.getPassword() + salt);
            user.setPassword(password);
            user.setStatus("正常");
            Integer insert = userDao.insert(user);
            User user1 = userDao.selectById(s);
            HashMap<String, Object> map = new HashMap<>();
            List<RegistMap> man = slelctMap("男");
            List<RegistMap> woman =slelctMap("女");
            map.put("man",man);
            map.put("woman",woman);
            String s1 = JSON.toJSONString(map);
            List<Month> months = slelctMonth();
            String s2 = JSON.toJSONString(months);
            GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io", "BC-798823e7d33c4c098f87da28c613fd78");
            goEasy.publish("qwshang", s1);
            goEasy.publish("qwshang2", s2);
            return user1;
        }else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("error",400);
            map.put("message","手机号已存在");
            return map;
        }

    }

    @Override
    public Object login(String phone,String password) {
        User user = userDao.selectByPhoneAndPassword(phone, password);
        if(user!=null){
            return user;
        }else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("error",-200);
            map.put("message","用户名不存在");
            return map;
        }

    }

    @Override
    public String add(User user) {
        String s = UUID.randomUUID().toString();
        user.setId(s);
        user.setRegistTime(new Date());
        String salt = MD5Utils.getSalt();
        user.setSalt(salt);
        String password = MD5Utils.getPassword(user.getPassword() + salt);
        user.setPassword(password);
        user.setStatus("正常");
        userDao.insert(user);
        return s;
    }

    @Override
    public void modifyProfile(User user) {
        userDao.updateProfile(user);
    }

    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Integer records = userDao.selectRecords();
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        List<User> users = userDao.selectAll((page - 1) * rows, rows);
        //当前页
        map.put("page", page);
        //总记录数
        map.put("records", records);
        //总页数
        map.put("total", total);
        //查询出的集合
        map.put("rows", users);
        return map;

    }

    @Override
    public void modifyStatus(User user) {
        userDao.updateStatus(user);
    }

    @Override
    public User queryById(String id) {
        User user = userDao.selectById(id);
        return user;
    }
}

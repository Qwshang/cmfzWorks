package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private HttpSession session;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> login(Admin admin) {
        Map<String, Object> map = new HashMap<>();
        Admin selectAdmin = adminDao.selectAdminByName(admin.getUsername());
        if (selectAdmin == null) {
            map.put("code", 300);
            map.put("message", "用户名错误");
        } else if(selectAdmin.getPassword().equals(admin.getPassword())){
            session.setAttribute("admin",selectAdmin);
            map.put("code", 200);
            map.put("message", "登陆成功");
        }else {
            map.put("code", 400);
            map.put("message", "密码错误");
        }
            return map;
    }
}

package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("admin")
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    public Map<String, Object> login(Admin admin) {
        Map<String, Object> login = adminService.login(admin);
        return login;
    }

}

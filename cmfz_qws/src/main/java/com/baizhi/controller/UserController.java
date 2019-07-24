package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.annotation.UserAnnotation;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baizhi.entity.RegistMap;
import com.baizhi.service.UserService;
import org.apache.http.HttpResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    //一键导入
    @RequestMapping("impot")
    public void impot(MultipartFile file) throws Exception {

        Workbook workbook = new HSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < lastRowNum; i++) {
            User user = new User();
            Row row = sheet.getRow(i + 1);
            Cell cell = row.getCell(0);
            user.setId(cell.getStringCellValue());
            Cell cell1 = row.getCell(1);
            Cell cell2 = row.getCell(2);
            Cell cell3 = row.getCell(3);
            Cell cell4 = row.getCell(4);
            Cell cell5 = row.getCell(5);
            Cell cell6 = row.getCell(6);
            Cell cell7 = row.getCell(7);
            Cell cell8 = row.getCell(8);
            Cell cell9 = row.getCell(9);
            Cell cell10 = row.getCell(10);
            Cell cell11 = row.getCell(11);
            user.setPhone(cell1.getStringCellValue());
            user.setPassword(cell2.getStringCellValue());
            user.setSalt(cell3.getStringCellValue());
            user.setDharmaName(cell4.getStringCellValue());
            user.setProvince(cell5.getStringCellValue());
            user.setCity(cell6.getStringCellValue());
            user.setGender(cell7.getStringCellValue());
            user.setPersonalSign(cell8.getStringCellValue());
            user.setProfile(cell9.getStringCellValue());
            user.setStatus(cell10.getStringCellValue());
            user.setRegistTime(cell11.getDateCellValue());
            list.add(user);
        }
        for (User user : list) {
            System.out.println(user);
        }

    }


    //一键导出
    @RequestMapping("out")
    public void out(HttpServletResponse response,HttpServletRequest request)throws Exception {
        List<User> users = userService.findAlls();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户表","用户表"),
                 User.class, users);
        //设置响应类型,              《《《下一行，写不写都行》》》
        response.setContentType(request.getSession().getServletContext().getMimeType("xls"));
        //设置响应头
        response.setHeader("content-disposition","attachment;fileName=user.xls");
        workbook.write(response.getOutputStream());
        workbook.close();

    }

    @RequestMapping("map")
    public HashMap<String, Object> map(){
        HashMap<String, Object> map = new HashMap<>();
        List<RegistMap> man = userService.slelctMap("男");
        List<RegistMap> woman = userService.slelctMap("女");
        map.put("man",man);
        map.put("woman",woman);
        return map;
    }

    @RequestMapping("month")
    public List<Month> month(){
        List<Month> months = userService.slelctMonth();
        return months;
    }
    @RequestMapping("login")
    public Object login(String phone,String password){
        Object login = userService.login(phone, password);
        return login;
    }

    @RequestMapping("regist")
    public Object regist(User user){
        Object regist = userService.regist(user);
        return regist;
    }

    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> map = userService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public String edit(String oper, User user){
        if ("edit".equals(oper)) {
            User user1 = userService.queryById(user.getId());
            userService.modifyStatus(user1);
            //执行修改代码
        } else if ("add".equals(oper)) {
            String s = userService.add(user);
            return s;
            //执行增加代码
        } else {
            //执行删除代码
        }
        return null;
    }

    @RequestMapping("updateStatus")
    public String updateStatus(String id){
        User user = userService.queryById(id);
        userService.modifyStatus(user);
        return "true";
    }
    @RequestMapping("upload")
    public void upload(String id , MultipartFile profile, HttpServletRequest request , HttpServletResponse response){
        //文件上传
        String originalFilename = profile.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("profilePic");
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        try {
            profile.transferTo(new File(path,originalFilename));
            //修改数据库的路径
            User user = new User();
            user.setId(id);
            user.setProfile(originalFilename);
            userService.modifyProfile(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.baizhi.test;

import com.baizhi.annotation.UserAnnotation;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPOI {

    @Autowired
    private UserDao userDao;

    @Test
    public void out() throws Exception {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("oneTable");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("编号");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue(new Date());
        CellStyle date = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        date.setDataFormat(format);
        cell1.setCellStyle(date);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setColor(Font.COLOR_RED);
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);

        File file = new File("E:/文件");
        if (!file.exists()) {
            file.mkdir();
        }
        workbook.write(new FileOutputStream("E:/文件/excel.xls"));
        workbook.close();
    }


    @Test
    public void user() throws Exception {
        List<User> users = userDao.selectAlls();
        Workbook workbook = new HSSFWorkbook();
        Sheet better = workbook.createSheet("用户表");
        Row row = better.createRow(0);
//        String[] titles = {"编号", "手机号", "密码", "盐", "法名", "省", "市", "性别", "个性签名", "头像", "状态", "注册时间"};
//        for (int i = 0; i < titles.length; i++) {
//            String title = titles[i];
//            Cell cell = row.createCell(i);
//            cell.setCellValue(title);
//        }

        Class<User> userClass = User.class;
        Field[] fields = userClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            UserAnnotation annotation = field.getAnnotation(UserAnnotation.class);
            String name = annotation.name();
            Cell cell = row.createCell(i);
            cell.setCellValue(name);

        }


        CellStyle dateStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        dateStyle.setDataFormat(format);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            Row row1 = better.createRow(i + 1);
            Cell cell = row1.createCell(0);
            cell.setCellValue(user.getId());
            Cell cell1 = row1.createCell(1);
            cell1.setCellValue(user.getPhone());
            Cell cell2 = row1.createCell(2);
            cell2.setCellValue(user.getPassword());
            Cell cell3 = row1.createCell(3);
            cell3.setCellValue(user.getSalt());
            Cell cell4 = row1.createCell(4);
            cell4.setCellValue(user.getDharmaName());
            Cell cell5 = row1.createCell(5);
            cell5.setCellValue(user.getProvince());
            Cell cell6 = row1.createCell(6);
            cell6.setCellValue(user.getCity());
            Cell cell8 = row1.createCell(7);
            cell8.setCellValue(user.getGender());
            Cell cell9 = row1.createCell(8);
            cell9.setCellValue(user.getPersonalSign());
            Cell cell10 = row1.createCell(9);
            cell10.setCellValue(user.getProfile());
            Cell cell11 = row1.createCell(10);
            cell11.setCellValue(user.getStatus());
            Cell cell12 = row1.createCell(11);
            cell12.setCellValue(user.getRegistTime());
            cell12.setCellStyle(dateStyle);

            workbook.write(new FileOutputStream("e:/文件/excel.xls"));
            workbook.close();
        }
    }

//    @Test
//    public void impot() throws Exception {
//        Workbook workbook = new HSSFWorkbook(new FileInputStream("E:/文件/excel.xls"));
//        Sheet sheet = workbook.getSheetAt(0);
//        int lastRowNum = sheet.getLastRowNum();
//        ArrayList<User> list = new ArrayList<>();
//        for (int i = 0; i < lastRowNum; i++) {
//            User user = new User();
//            Row row = sheet.getRow(i + 1);
//            Cell cell = row.getCell(0);
//            user.setId(cell.getStringCellValue());
//            Cell cell1 = row.getCell(1);
//            Cell cell2 = row.getCell(2);
//            Cell cell3 = row.getCell(3);
//            Cell cell4 = row.getCell(4);
//            Cell cell5 = row.getCell(5);
//            Cell cell6 = row.getCell(6);
//            Cell cell7 = row.getCell(7);
//            Cell cell8 = row.getCell(8);
//            Cell cell9 = row.getCell(9);
//            Cell cell10 = row.getCell(10);
//            Cell cell11 = row.getCell(11);
//            user.setPhone(cell1.getStringCellValue());
//            user.setPassword(cell2.getStringCellValue());
//            user.setSalt(cell3.getStringCellValue());
//            user.setDharmaName(cell4.getStringCellValue());
//            user.setProvince(cell5.getStringCellValue());
//            user.setCity(cell6.getStringCellValue());
//            user.setGender(cell7.getStringCellValue());
//            user.setPersonalSign(cell8.getStringCellValue());
//            user.setProfile(cell9.getStringCellValue());
//            user.setStatus(cell10.getStringCellValue());
//            user.setRegistTime(cell11.getDateCellValue());
//            list.add(user);
//        }
//        for (User user : list) {
//            System.out.println(user);
//        }
//
//    }


}
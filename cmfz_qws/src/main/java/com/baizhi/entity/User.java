package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baizhi.annotation.UserAnnotation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Excel(name = "编号")
    @UserAnnotation(name = "编号")
    private String id;
    @Excel(name = "手机号")
    @UserAnnotation(name = "手机号")
    private String phone;
    @Excel(name = "密码")
    @UserAnnotation(name = "密码")
    private String password;
    @Excel(name = "盐")
    @UserAnnotation(name = "盐")
    private String salt;
    @Excel(name = "法号")
    @UserAnnotation(name = "法号")
    private String dharmaName;
    @Excel(name = "省")
    @UserAnnotation(name = "省")
    private String province;
    @Excel(name = "市")
    @UserAnnotation(name = "市")
    private String city;
    @Excel(name = "性别")
    @UserAnnotation(name = "性别")
    private String gender;
    @Excel(name = "个性签名")
    @UserAnnotation(name = "签名")
    private String personalSign;
    @Excel(name = "头像")
    @UserAnnotation(name = "头像")
    private String profile;
    @Excel(name = "状态")
    @UserAnnotation(name = "状态")
    private String status;
    @Excel(name = "注册时间", format = "yyyy-MM-dd")
    @UserAnnotation(name = "注册时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date registTime;

}

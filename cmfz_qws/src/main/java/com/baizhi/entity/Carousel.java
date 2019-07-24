package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carousel implements Serializable {
/*   -- 轮播图ID
    carousel_id          varchar(50) not null,
    -- 名称
    title                 varchar(50),
   -- 图片路径
    imgPath              varchar(200),
   -- 图片状态
    status               varchar(10),
   -- 创建时间
    create_time          date,*/
    private String id;
    private String title;
    private String imgPath;
    private String status;
    private String createTime;

}

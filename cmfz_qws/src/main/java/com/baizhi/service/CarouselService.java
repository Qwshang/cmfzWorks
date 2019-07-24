package com.baizhi.service;

import com.baizhi.dto.CarouselPagrDto;
import com.baizhi.entity.Carousel;

public interface CarouselService {
    //插入一条数据
    String add(Carousel carousel);

    //删除一条数据
    void remove(String id);

    //修改一条数据
    String modify(Carousel carousel);

    //查询一条数据，根据ID
    Carousel findById(String id);

    // 总行数
    Integer findTotalCount();

    // 当前页的数据行
    CarouselPagrDto findByPage(Integer page, Integer rows);

    //修改图片路径
    void modifyImgPath(Carousel carousel);



}

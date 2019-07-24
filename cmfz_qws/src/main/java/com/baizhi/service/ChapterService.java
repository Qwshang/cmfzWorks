package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    //分页查所有
    Map<String , Object> queryAll(Integer page, Integer rows, String albumId);
    //添加
    String addChapter(Chapter chapter);
    //修改图片路径方法
    void modifyDownpath(Chapter chapter);
}

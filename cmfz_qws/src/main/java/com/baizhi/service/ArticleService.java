package com.baizhi.service;

import com.baizhi.entity.Article;
import java.util.Map;

public interface ArticleService {
    //插入一条数据
    void add(Article article);

    //删除一条数据
    void remove(String id);


    //查询一条数据，根据ID
    Article findById(String id);

    //分页查所有
    Map<String , Object> queryAll(Integer page, Integer rows);

}
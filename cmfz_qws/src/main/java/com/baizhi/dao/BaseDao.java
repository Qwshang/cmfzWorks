package com.baizhi.dao;

import java.util.List;

public interface BaseDao<T> {
    //分页查所有
    List<T> selectAll(Integer begin, Integer rows);
    //查一个
    T selectById(String id);
    //添加
    Integer insert(T t);
    //查询总条数
    Integer selectRecords();
    //删除
    void deleteById(String id);

}

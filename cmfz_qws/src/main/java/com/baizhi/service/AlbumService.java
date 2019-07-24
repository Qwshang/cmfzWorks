package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    //分页查所有
    Map<String , Object> queryAll(Integer page, Integer rows);
    //添加
    String addAlbum(Album album);
    //修改图片路径方法
    void modifyCover(Album album);
}

package com.baizhi.dao;

import com.baizhi.entity.Album;

public interface AlbumDao extends BaseDao<Album> {
    //修改封面图
    void updateCover(Album album);
}

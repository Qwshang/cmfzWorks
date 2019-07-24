package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao extends BaseDao<Chapter> {
    //覆盖继承的方法，根据专辑ID，分页查，专辑下的音频所有
    List<Chapter> selectAll(@Param("begin") Integer begin, @Param("rows") Integer rows, @Param("albumId") String albumId);
    //修改下载路径
    void updateDownpath(Chapter chapter);

}

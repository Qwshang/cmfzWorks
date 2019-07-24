package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;


    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Integer records = albumDao.selectRecords();
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        Integer begin=(page-1)*rows;
        List<Album> albums = albumDao.selectAll(begin, rows);
        //当前页
        map.put("page",page);
        //总条数
        map.put("total", total);
        //总页数
        map.put("records",records);
        //当前页结果集
        map.put("rows",albums);
        return map;
    }

    @Override
    public String addAlbum(Album album) {
        String s = UUID.randomUUID().toString();
        album.setId(s);
        albumDao.insert(album);
        return s;
    }

    @Override
    public void modifyCover(Album album) {
        albumDao.updateCover(album);
    }
}

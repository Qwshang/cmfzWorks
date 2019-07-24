package com.baizhi.test;

import com.baizhi.CmfzQwsApplication;
import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest(classes = CmfzQwsApplication.class)
@RunWith(value = SpringRunner.class)
public class TestAlbum {
    @Autowired
    private AlbumDao albumDao;

    @Test
    public void dfweasd(){
        List<Album> albums = albumDao.selectAll(0, 5);
        for (Album album : albums) {
            System.out.println(album);
            System.out.println("qqqqqqq");
        }


    }



}

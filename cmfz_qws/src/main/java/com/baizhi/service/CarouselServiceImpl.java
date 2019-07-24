package com.baizhi.service;

import com.baizhi.dao.CarouselDao;
import com.baizhi.dto.CarouselPagrDto;
import com.baizhi.entity.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselDao carouselDao;


    @Override
    public String add(Carousel carousel) {
        String s = UUID.randomUUID().toString();
        carousel.setId(s);
        carouselDao.insert(carousel);
        return s;
    }

    @Override
    public void remove(String id) {
        carouselDao.delete(id);
    }

    @Override
    public String modify(Carousel carousel) {
        if(carousel.getImgPath()==""){
            Carousel carousel1 = carouselDao.selectById(carousel.getId());
            String imgPath = carousel1.getImgPath();
            carousel.setImgPath(imgPath);
        }
        carouselDao.update(carousel);
        return carousel.getId();
    }

    @Override
    public Carousel findById(String id) {
        Carousel carousel = carouselDao.selectById(id);
        return carousel;
    }

    @Override
    public Integer findTotalCount() {
        Integer count = carouselDao.selectTotalCount();
        return count;
    }

    @Override
    public CarouselPagrDto findByPage(Integer page, Integer rows) {
        CarouselPagrDto dto = new CarouselPagrDto();
        Integer count = carouselDao.selectTotalCount();
        List<Carousel> carousels = carouselDao.selectByPage(page, rows);
        dto.setPage(page);
        dto.setRecords(count);
        dto.setRows(carousels);
        dto.setTotal(count%rows==0?count/rows:count/rows+1);
        return dto;
    }


    @Override
    public void modifyImgPath(Carousel carousel) {
        carouselDao.updateimgPath(carousel);
    }
}

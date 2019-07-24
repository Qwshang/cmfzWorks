package com.baizhi.controller;

import com.baizhi.dto.CarouselPagrDto;
import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;


    @RequestMapping("queryByPage")
    public CarouselPagrDto findAll(Integer rows,Integer page){
        CarouselPagrDto carouselPagrDto = carouselService.findByPage(page, rows);
        return carouselPagrDto;
    }

    //增删改，和一起
    @RequestMapping("edit")
    @ResponseBody
    public String edit(Carousel carousel, String oper,String[] id){
        if("add".equals(oper)){
            String s = carouselService.add(carousel);
            return s;
        }else if("edit".equals(oper)){
            String s = carouselService.modify(carousel);
            return s;
        }else {
            for (String s : id) {
                carouselService.remove(s);
            }
        }
        return null;
    }

//    @RequestMapping("upload")
//    public void upload(String id , MultipartFile file, HttpServletResponse response, HttpServletRequest request){
//        //文件上传
//        String filename = file.getOriginalFilename();
//        String path = request.getSession().getServletContext().getRealPath("carouselPic");
//        File file1 = new File(path);
//        if(!file1.exists()){
//            file1.mkdir();
//        }
//        try {
//            file.transferTo(new File(path,filename));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //修改数据库的路径
//        Carousel carousel = new Carousel();
//        carousel.setCarouselId(id);
//        carousel.setImgPath(filename);
//        carouselService.modifyImgPath(carousel);
//    }
    @RequestMapping("upload")
    public void upload(String id , MultipartFile imgPath, HttpServletRequest request , HttpServletResponse response){
        //文件上传
        String originalFilename = imgPath.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("carouselPic");
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        try {
            imgPath.transferTo(new File(path,originalFilename));
            //修改数据库的路径
            Carousel carousel = new Carousel();
            carousel.setId(id);
            carousel.setImgPath(originalFilename);
            carouselService.modifyImgPath(carousel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

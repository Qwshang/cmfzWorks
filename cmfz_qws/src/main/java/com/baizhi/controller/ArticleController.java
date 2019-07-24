package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("queryAll")
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> queryAll = articleService.queryAll(page, rows);
        return queryAll;
    }
    @RequestMapping("addArticle")
    public void addArticle(Article article){
        String s = UUID.randomUUID().toString();
        article.setId(s);
        article.setPublishTime(new Date());
        articleService.add(article);
    }



    @RequestMapping("upload")
    public Map<String, Object> upload(MultipartFile file, HttpSession session){
        String filename = file.getOriginalFilename();
        String path = session.getServletContext().getRealPath("articlePic");
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }
        HashMap<String, Object> map = new HashMap<>();
        try {
            file.transferTo(new File(path,filename));
            map.put("error",0);
            map.put("url","http://localhost:8989/cmfz/articlePic/"+filename);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error",1);
            map.put("url","http://localhost:8989/cmfz/articlePic/"+filename);
            return map;
        }


    }


}

package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public void add(Article article) {
        String s = UUID.randomUUID().toString();
        article.setId(s);
        article.setPublishTime(new Date());
        articleDao.insert(article);
    }

    @Override
    public void remove(String id) {
        articleDao.deleteById(id);
    }

    @Override
    public Article findById(String id) {
        Article article = articleDao.selectById(id);
        return article;
    }

    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Integer records = articleDao.selectRecords();
        Integer total = records%rows == 0?records/rows :records/rows+1;
        List<Article> articles = articleDao.selectAll((page-1)*rows, rows);
        //当前页
        map.put("page",page);
        //总记录数
        map.put("records",records);
        //总页数
        map.put("total",total);
        //查询出的集合
        map.put("rows",articles);
        return map;
    }
}

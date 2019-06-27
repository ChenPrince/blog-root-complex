package com.zerodg.serviceImpl;

import com.zerodg.service.ArticleService;
import com.zerodg.vwdao.mapper.ArticleMapper;
import com.zerodg.vwentity.entity.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * create by  LZH on 2019/6/25
 */
@Service
//全局事务
@Transactional
public  class ArticleServiceImpl implements ArticleService{

    @Resource
    private  ArticleMapper articleMapper;

    @Override
    public List<Article> selectBySort(String sort) {

        List<Article> articleList=articleMapper.selectBySort(sort);
        return articleList;
    }

    @Override
    public List<Article> SelectArticleSortByTime() {

        List<Article> articleList=articleMapper.selectArticleByTime();
        List<Article> articles=new ArrayList<>();

        Integer count=5,i=0;
        for(Article article:articleList){
            if(i<count){
                articles.add(article);
            }
            i++;
        }
        return articles;
    }

    @Override
    public List<Article> SelectArticleSortByStar() {
        List<Article> articleList=articleMapper.selectArticleByStar();
        List<Article> articles=new ArrayList<>();

        Integer count=5,i=0;
        for(Article article:articleList){
            if(i<count){
                articles.add(article);
            }
            i++;
        }
        return articles;
    }
}

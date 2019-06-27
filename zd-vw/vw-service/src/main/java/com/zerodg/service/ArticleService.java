package com.zerodg.service;

import com.zerodg.vwentity.entity.Article;

import java.util.List;

/**
 * create by  LZH on 2019/6/25
 */
public interface ArticleService {
    List<Article> selectBySort(String sort);

    List<Article> SelectArticleSortByTime();

    List<Article> SelectArticleSortByStar();
}

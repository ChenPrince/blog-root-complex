package com.zerodg.service;

import com.zerodg.vwentity.entity.Article;
import com.zerodg.vwentity.entity.Carousel;

import java.util.List;

/**
 * create by Administrator on 13:10 2019/6/25 0025
 */
public interface SY01Service {

    List<Carousel> getCarouselList();

    List<Article> getArticleList();

    List getSortList();
}

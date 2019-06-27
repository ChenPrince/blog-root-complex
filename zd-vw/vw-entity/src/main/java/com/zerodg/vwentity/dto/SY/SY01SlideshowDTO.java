package com.zerodg.vwentity.dto.SY;

import com.zerodg.vwentity.entity.Article;
import com.zerodg.vwentity.entity.Carousel;

import java.util.Date;
import java.util.List;

/**
 * create by Administrator on 15:28 2019/6/25 0025
 */
public class SY01SlideshowDTO {
    private List<Carousel> carouseList;
    private List<Carousel> articlefousList;

    public List<Carousel> getCarouseList() {
        return carouseList;
    }

    public void setCarouseList(List<Carousel> carouseList) {
        this.carouseList = carouseList;
    }

    public List<Carousel> getArticlefousList() {
        return articlefousList;
    }

    public void setArticlefousList(List<Carousel> articlefousList) {
        this.articlefousList = articlefousList;
    }

}

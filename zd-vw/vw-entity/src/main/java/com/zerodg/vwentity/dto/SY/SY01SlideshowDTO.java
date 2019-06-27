package com.zerodg.vwentity.dto.SY;

import com.zerodg.vwentity.entity.Article;

import java.util.Date;
import java.util.List;

/**
 * create by Administrator on 15:28 2019/6/25 0025
 */
public class SY01SlideshowDTO {
    private List<Article> carouseList;
    private List<Article> articlefousList;

    public List<Article> getCarouseList() {
        return carouseList;
    }

    public void setCarouseList(List<Article> carouseList) {
        this.carouseList = carouseList;
    }

    public List<Article> getArticlefousList() {
        return articlefousList;
    }

    public void setArticlefousList(List<Article> articlefousList) {
        this.articlefousList = articlefousList;
    }

    private Integer id;

    private Integer articleId;

    private String title;

    private Date createAt;

    private Integer isSelect;

    public Integer getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Integer isSelect) {
        this.isSelect = isSelect;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}

package com.zerodg.vwentity.dto.Article;

import java.util.Date;

public class ArticleContentDTO {
    private Integer id;

    private Integer userId;

    private Integer star;

    private Integer diss;

    private Integer visit;

    private String content;

    private String sort;

    private Date createAt;

    private String createTime;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getDiss() {
        return diss;
    }

    public void setDiss(Integer diss) {
        this.diss = diss;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}

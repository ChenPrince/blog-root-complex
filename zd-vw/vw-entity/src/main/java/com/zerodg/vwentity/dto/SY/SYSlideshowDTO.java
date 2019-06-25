package com.zerodg.vwentity.dto.SY;

import java.util.Date;

/**
 * create by Administrator on 15:28 2019/6/25 0025
 */
public class SYSlideshowDTO {
    private Integer id;

    private Integer articleId;

    private String title;

    private Date createAt;

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

package com.zerodg.vwentity.dto.Personal;

/**
 * @program: eroot
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-10-25-16:20
 **/
public class GetMyArticlesDTO {

    private Integer articleId;

    private String articleTitle;

    private String articleAbstract;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }
}

package com.zerodg.vwentity.dto.SY;

import com.zerodg.vwentity.entity.Article;

import java.util.Date;
import java.util.List;

public class SY01ArticleDTO {
    private List<Article> articleList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}

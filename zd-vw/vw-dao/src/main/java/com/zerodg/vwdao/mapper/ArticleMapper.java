package com.zerodg.vwdao.mapper;

import com.zerodg.vwentity.entity.Article;
import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    List<Article> selectBySort(String sort);

    List<Article> selectArticleByTime();

    List<Article> selectArticleByStar();
}
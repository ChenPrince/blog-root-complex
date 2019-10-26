package com.zerodg.vwdao.mapper;

import com.zerodg.vwentity.entity.Article;
import org.apache.ibatis.annotations.Param;

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

    List<Article> selectArticleByUserId(@Param("user_id") Integer userId);

    List<Article> selectSort();

    Integer selectUserId(Integer id);

    void insertArticle(@Param("userId")Integer userId,@Param("content")String content,@Param("time")String time,@Param("title")String title,@Param("articleType") String articleType);



}
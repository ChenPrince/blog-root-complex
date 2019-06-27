package com.zerodg.service;

import com.zerodg.vwentity.dto.Article.ArticleAuthorDTO;
import com.zerodg.vwentity.dto.Article.ArticleCommentDTO;
import com.zerodg.vwentity.dto.Article.ArticleContentDTO;

import java.util.List;

/*
 *create by loser on 2019/6/25
 */
public interface ArticleService {

    ArticleContentDTO getArticleId(Integer id);

    Integer getUserId(Integer id);

    ArticleCommentDTO getCommentById(Integer id);

    ArticleAuthorDTO getUserById(Integer user_id);
}

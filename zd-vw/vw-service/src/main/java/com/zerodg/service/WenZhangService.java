package com.zerodg.service;

import com.zerodg.vwentity.dto.WenZhang.WenZhang_ArticleDTO;
import com.zerodg.vwentity.entity.Article;
import com.zerodg.vwentity.entity.Comment;

import java.util.List;

/*
 *create by loser on 2019/6/25
 */
public interface WenZhangService {
    List<WenZhang_ArticleDTO> getArticleListByUserid(Integer id);

    Article getArticleId(Integer id);

    Integer getUserId(Integer id);

    List<Comment> getCommentById(Integer id);
}

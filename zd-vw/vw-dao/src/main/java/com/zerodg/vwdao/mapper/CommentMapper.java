package com.zerodg.vwdao.mapper;

import com.zerodg.vwentity.entity.Article;
import com.zerodg.vwentity.entity.Comment;

import java.util.List;

/**
 * create by Administrator on 19:51 2019/6/25 0025
 */
public interface CommentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    List<Comment> selectIdByArticleid(Integer id);
}

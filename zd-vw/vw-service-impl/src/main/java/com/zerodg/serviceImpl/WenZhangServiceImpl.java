package com.zerodg.serviceImpl;

import com.zerodg.service.WenZhangService;
import com.zerodg.vwdao.mapper.ArticleMapper;
import com.zerodg.vwdao.mapper.CommentMapper;
import com.zerodg.vwentity.dto.WenZhang.WenZhang_ArticleDTO;
import com.zerodg.vwentity.entity.Article;
import com.zerodg.vwentity.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/*
 *create by loser on 2019/6/25
 */
@Service
public class WenZhangServiceImpl implements WenZhangService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<WenZhang_ArticleDTO> getArticleListByUserid(Integer id) {

        /*System.out.println("getbyid");
        System.out.println(id+"-------------------");

        List<WenZhang_ArticleDTO> articles = articleMapper.selectArticlesById(id);
        System.out.println(articles.get(0).getId()+"------------------------");
       *//* for (WZ001A04DTO article : articles) {
            // System.out.println(article.getId()+"------------------------------------");
                *//**//*if (article.getId()==id))
                {
                    articles.remove(article;
                }*//**//*
        }*//*
        return articles;

        //return null;*/
        System.out.println("gisgcbkusbks");
        System.out.println(articleMapper.selectAll().get(0).getId()+"=======================");
        return null;
    }

    //通过id获取文章内容
    @Override
    public Article getArticleId(Integer id) {
        Article article=articleMapper.selectByPrimaryKey(id);

        return article;
    }
    //通过文章id获取作者id
    @Override
    public Integer getUserId(Integer id) {
        Integer user_id=articleMapper.selectUserId(id);

        return user_id;
    }

    /**
     * 通过文章id获取文章评论内容
     * @param id
     * @return
     */
    @Override
    public List<Comment> getCommentById(Integer id) {
        List<Comment> commentLists=commentMapper.selectAllByArticleid(id);
        //处理评论内容，只显示100个字，其余省略
        for (Comment commentList : commentLists) {
            if(commentList.getComment().length()>100)
            {
                commentList.setComment(commentList.getComment().substring(0,100)+"....");
            }
        }

        return commentLists;
    }

}

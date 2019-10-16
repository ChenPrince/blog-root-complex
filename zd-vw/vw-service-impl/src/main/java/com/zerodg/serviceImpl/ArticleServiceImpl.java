package com.zerodg.serviceImpl;

import com.zerodg.service.ArticleService;
import com.zerodg.vwdao.mapper.ArticleMapper;
import com.zerodg.vwdao.mapper.CommentMapper;
import com.zerodg.vwdao.mapper.UserMapper;
import com.zerodg.vwentity.dto.Article.ArticleAuthorDTO;
import com.zerodg.vwentity.dto.Article.ArticleCommentDTO;
import com.zerodg.vwentity.dto.Article.ArticleContentDTO;
import com.zerodg.vwentity.entity.Article;
import com.zerodg.vwentity.entity.Comment;
import com.zerodg.vwentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerodg.vwentity.entity.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
 *create by LZH on 2019/6/25
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserMapper userMapper;


    //通过id获取文章内容
    @Override
    public ArticleContentDTO getArticleId(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article != null) {
            ArticleContentDTO articleContentDTO = new ArticleContentDTO();
            articleContentDTO.setId(article.getId());
            articleContentDTO.setContent(article.getContent());
            articleContentDTO.setCreateAt(article.getCreateAt());
            articleContentDTO.setDiss(article.getDiss());
            articleContentDTO.setStar(article.getStar());
            articleContentDTO.setUserId(article.getUserId());
            articleContentDTO.setVisit(article.getVisit());
            articleContentDTO.setSort(article.getSort());
            //System.out.println(article.getSort());
            articleContentDTO.setTitle(article.getTitle());


            //处理时间
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sf.format(article.getCreateAt());
            articleContentDTO.setCreateTime(str);
            return articleContentDTO;
        } else {
            return null;
        }
    }

    //通过文章id获取作者id
    @Override
    public Integer getUserId(Integer id) {
        Integer user_id = articleMapper.selectUserId(id);

        return user_id;
    }

    /**
     * 通过文章id获取文章评论内容
     *
     * @param id
     * @return
     */
    @Override
    public ArticleCommentDTO getCommentById(Integer id) {
        System.out.println(id);
        ArticleCommentDTO articleCommentDTOList = new ArticleCommentDTO();

        List<Comment> commentLists = commentMapper.selectIdByArticleid(id);//获取的同文章评论

        //处理时间
        for (Comment commentList : commentLists) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sf.format(commentList.getCreateAt());
            commentList.setCreateTime(str);
        }

        articleCommentDTOList.setCommentList(commentLists);


        return articleCommentDTOList;
    }

    /**
     * 通过id获取作者信息
     *
     * @param user_id
     * @return
     */
    @Override
    public ArticleAuthorDTO getUserById(Integer user_id) {
        User user = userMapper.selectByPrimaryKey(user_id);
        //System.out.println("user:"+user);
        ArticleAuthorDTO articleAuthorDTO = new ArticleAuthorDTO();
        articleAuthorDTO.setId(user.getId());
        articleAuthorDTO.setArea(user.getArea());
        articleAuthorDTO.setAvatarUrl(user.getAvatarUrl());
        articleAuthorDTO.setBirthday(user.getBirthday());
        articleAuthorDTO.setEmail(user.getEmail());
        articleAuthorDTO.setName(user.getName());
        articleAuthorDTO.setPassword(user.getPassword());
        articleAuthorDTO.setRegistAt(user.getRegistAt());
        articleAuthorDTO.setSex(user.getSex());
        articleAuthorDTO.setSign(user.getSign());
        articleAuthorDTO.setUserName(user.getUserName());

        //处理时间
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sf.format(user.getBirthday());
        String str1 = sf.format(user.getRegistAt());
        articleAuthorDTO.setBirthdayTime(str);
        articleAuthorDTO.setRegistTime(str1);

        return articleAuthorDTO;
    }

    @Override
    public void insertArticle(Integer userId, String title, String content,String articleType) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String time=dateFormat.format(date);
        System.out.println(time);
        articleMapper.insertArticle(userId,content,time,title,articleType);
    }

}

package com.zerodg.vwapi.controller;

/*
 *create by loser on 2019/6/25
 */


import com.zerodg.service.ArticleService;
import com.zerodg.vwentity.dto.Article.*;
import com.zerodg.zdutil.util.JSONResult;
import com.zerodg.zdutil.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * create by  LZH on 2019/6/25
 */
@SuppressWarnings("ALL")
@RestController
@Api(description = "文章")
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    /**
     * 通过文章id获取文章具体内容
     * @param articleContentInputDTO
     * @return
     */
    @ApiOperation(value = "获取文章详情",notes = "根据文章的id来获取文章的基本信息")
   // @ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "String",paramType = "path")
    @RequestMapping(value = "/content",method = RequestMethod.GET)
    public JSONResult getArticleById(@Valid ArticleContentInputDTO articleContentInputDTO){
        JSONResult<ArticleContentDTO> jsonResult=new JSONResult<>();

        //System.out.println("1id=="+articleContentInputDTO.getId());
        ArticleContentDTO article=articleService.getArticleId(articleContentInputDTO.getId());
        if(article!=null)
        {
            jsonResult.setData(article);
            jsonResult.setMessage(new Message("文章查找成功"));
            return jsonResult;
        }else {
            jsonResult.setMessage(new Message("文章查找失败"));
            return jsonResult;
        }
    }

    /**
     * 获取相应文章的作者简介
     * @param articleAuthorInputDTO
     * @return
     */
    @ApiOperation(value = "获取文章作者详情",notes = "根据文章的id来获取文章作者的基本信息")
    //@ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "String",paramType = "path")
    @RequestMapping(value = "/author",method = RequestMethod.GET)
    public JSONResult getAuthorById(@Valid ArticleAuthorInputDTO articleAuthorInputDTO){
        JSONResult<ArticleAuthorDTO> jsonResult=new JSONResult<>();


        //通过文章id获取作者id
        Integer user_id=articleService.getUserId(articleAuthorInputDTO.getId());
        //通过作者id获取作者简介
        ArticleAuthorDTO articleAuthorDTO=articleService.getUserById(user_id);
        if(articleAuthorDTO!=null)
        {
            jsonResult.setData(articleAuthorDTO);
            jsonResult.setMessage(new Message("文章作者查找成功"));
            return jsonResult;
        }else {
            jsonResult.setMessage(new Message("文章作者查找失败"));
            return jsonResult;
        }
    }

    /**
     * 获取相应文章的评论信息
     * @param articleCommentInputDTO
     * @return
     */
    @ApiOperation(value = "获取文章评论详情",notes = "根据文章的id来获取文章评论信息")
    @RequestMapping(value = "/commentList",method = RequestMethod.GET)
    public JSONResult getCommentById(@Valid ArticleCommentInputDTO articleCommentInputDTO){
        JSONResult<ArticleCommentDTO> jsonResult=new JSONResult<>();


        Integer article_id=articleCommentInputDTO.getArticleId();
        //通过作者id获取评论
        ArticleCommentDTO articleCommentDTOS= articleService.getCommentById(article_id);

        if(articleCommentDTOS!=null)
        {
            jsonResult.setData(articleCommentDTOS);
            jsonResult.setMessage(new Message("文章评论查找成功"));
            return jsonResult;
        }else {
            jsonResult.setMessage(new Message("文章评论查找失败"));
            return jsonResult;
        }
    }

}

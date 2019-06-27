package com.zerodg.vwapi.controller;

/*
 *create by loser on 2019/6/25
 */

import com.zerodg.service.PHService;
import com.zerodg.service.ArticleService;
import com.zerodg.vwentity.dto.Article.*;
import com.zerodg.vwentity.entity.Comment;
import com.zerodg.vwentity.entity.User;
import com.zerodg.zdutil.util.JSONResult;
import com.zerodg.zdutil.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import com.zerodg.service.ArticleService;
import com.zerodg.vwentity.dto.SY01LoveInputDTO.SY01LoveInputDTO;
import com.zerodg.vwentity.dto.SY01SortInputDTO.SY01SortInputDTO;
import com.zerodg.vwentity.dto.SY01TimeInputDTO.SY01TimeInputDTO;
import com.zerodg.vwentity.entity.Article;
import com.zerodg.zdutil.util.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * create by  LZH on 2019/6/25
 */
@SuppressWarnings("ALL")
@RestController
@Api(description = "文章详情")
@RequestMapping("/Article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value="首页文章分类",notes="首页文章分类",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/sort",method= RequestMethod.GET)
    public  JSONResult login(@Valid SY01SortInputDTO input){

        JSONResult jsonResult=new JSONResult();

        List<Article> articleList=articleService.selectBySort(input.getSort());
        jsonResult.setData(articleList);
        return jsonResult;
    }
    @ApiOperation(value="首页右侧文章推荐（时间）",notes="首页右侧文章推荐（时间）",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/time",method= RequestMethod.GET)
    public JSONResult time(@Valid SY01TimeInputDTO input){

        JSONResult jsonResult=new JSONResult();
        List<Article> articleList=articleService.SelectArticleSortByTime();
        jsonResult.setData(articleList);

        return jsonResult;

    }

    @ApiOperation(value="首页右侧文章推荐（点赞）",notes="首页右侧文章推荐（点赞）",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/love",method= RequestMethod.GET)
    public JSONResult love(@Valid SY01LoveInputDTO input) {

        JSONResult jsonResult = new JSONResult();
        List<Article> articleList = articleService.SelectArticleSortByStar();
        jsonResult.setData(articleList);

        return jsonResult;

    }

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

        //System.out.println("2id=="+articleAuthorInputDTO.getId());
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
    //@ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "String",paramType = "path")
    @RequestMapping(value = "/commentList",method = RequestMethod.GET)
    public JSONResult getCommentById(@Valid ArticleCommentInputDTO articleCommentInputDTO){
        JSONResult<ArticleCommentDTO> jsonResult=new JSONResult<>();

        //System.out.println("3id=="+articleCommentInputDTO.getId());
        Integer article_id=articleCommentInputDTO.getArticleId();
        //通过作者id获取评论
        //System.out.println("aid="+article_id);
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

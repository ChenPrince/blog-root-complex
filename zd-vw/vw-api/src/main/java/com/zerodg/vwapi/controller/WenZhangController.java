package com.zerodg.vwapi.controller;

/*
 *create by loser on 2019/6/25
 */

import com.zerodg.service.PHService;
import com.zerodg.service.WenZhangService;
import com.zerodg.vwentity.dto.WenZhang.WenZhang_ArticleDTO;
import com.zerodg.vwentity.dto.WenZhang.WenZhang_ArticleInputDTO;
import com.zerodg.vwentity.entity.Article;
import com.zerodg.vwentity.entity.Comment;
import com.zerodg.vwentity.entity.User;
import com.zerodg.zdutil.util.JSONResult;
import com.zerodg.zdutil.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(description = "文章详细内容")
@RequestMapping("/Article")
@CrossOrigin
public class WenZhangController {

    @Autowired
    private WenZhangService wenZhangService;

    @Autowired
    private PHService phService;

    /**
     * 返回一个作者的所有文章 ，除了当时所选的文章
     * @return
     */
    @ApiOperation(value="作者相关文章获取",notes="作者相关文章获取",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/getArticleListByUserid",method= RequestMethod.GET)
    public JSONResult getArticleListByUserid(@Valid WenZhang_ArticleInputDTO wenZhang_articleInputDTO){

        System.out.println(wenZhang_articleInputDTO.getId()+"---");

        JSONResult<List<WenZhang_ArticleDTO>> jsonResult=new JSONResult<>();

        List<WenZhang_ArticleDTO> articleList=null;


        articleList=wenZhangService.getArticleListByUserid(wenZhang_articleInputDTO.getId());

        if(articleList!=null)
        {
            jsonResult.setData(articleList);


            jsonResult.setMessage(new Message("文章查找成功"));

            return jsonResult;
        }else {
            jsonResult.setMessage(new Message("文章查找为空"));

            return jsonResult;
        }


    }


    /**
     * 通过文章id获取文章具体内容
     * @param wenZhang_articleInputDTO
     * @return
     */
    @ApiOperation(value = "获取文章详情",notes = "根据url的id来获取文章的基本信息")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "String",paramType = "path")
    @RequestMapping(value = "/articleContent",method = RequestMethod.GET)
    public JSONResult getArticleById(@Valid WenZhang_ArticleInputDTO wenZhang_articleInputDTO){
        JSONResult<Article> jsonResult=new JSONResult<>();

        Article article=wenZhangService.getArticleId(wenZhang_articleInputDTO.getId());

        jsonResult.setData(article);
        jsonResult.setMessage(new Message("文章查找成功"));
        return jsonResult;
    }

    /**
     * 获取相应文章的作者简介
     * @param wenZhang_articleInputDTO
     * @return
     */
    @ApiOperation(value = "获取文章作者详情",notes = "根据url的id来获取文章作者的基本信息")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "String",paramType = "path")
    @RequestMapping(value = "/articleAuthor",method = RequestMethod.GET)
    public JSONResult getAuthorById(@Valid WenZhang_ArticleInputDTO wenZhang_articleInputDTO){
        JSONResult<User> jsonResult=new JSONResult<>();

        //通过文章id获取作者id
        Integer user_id=wenZhangService.getUserId(wenZhang_articleInputDTO.getId());
        //通过作者id获取作者简介
        User user=phService.getUserById(user_id);

        jsonResult.setData(user);
        jsonResult.setMessage(new Message("文章作者查找成功"));
        return jsonResult;
    }

    /**
     * 获取相应文章的评论信息
     * @param wenZhang_articleInputDTO
     * @return
     */
    @ApiOperation(value = "获取文章评论详情",notes = "根据url的id来获取文章评论信息")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "String",paramType = "path")
    @RequestMapping(value = "/articleComment",method = RequestMethod.GET)
    public JSONResult getCommentById(@Valid WenZhang_ArticleInputDTO wenZhang_articleInputDTO){
        JSONResult<List<Comment>> jsonResult=new JSONResult<>();

        Integer id=wenZhang_articleInputDTO.getId();
        //通过作者id获取评论
        List<Comment> comments= wenZhangService.getCommentById(id);

        jsonResult.setData(comments);
        jsonResult.setMessage(new Message("文章评论查找成功"));
        return jsonResult;
    }

}

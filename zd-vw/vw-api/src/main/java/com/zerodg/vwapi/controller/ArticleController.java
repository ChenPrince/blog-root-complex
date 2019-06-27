package com.zerodg.vwapi.controller;

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
@Api(description = "首页")
@RequestMapping("/SY01")
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
    public JSONResult love(@Valid SY01LoveInputDTO input){

        JSONResult jsonResult=new JSONResult();
        List<Article> articleList=articleService.SelectArticleSortByStar();
        jsonResult.setData(articleList);

        return jsonResult;

    }

}

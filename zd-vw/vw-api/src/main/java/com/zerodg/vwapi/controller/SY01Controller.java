package com.zerodg.vwapi.controller;

import com.zerodg.service.SY01Service;
import com.zerodg.vwentity.dto.SY.SYSlideshowDTO;
import com.zerodg.vwentity.dto.WenZhang.WenZhang_ArticleDTO;
import com.zerodg.vwentity.entity.Article;
import com.zerodg.vwentity.entity.Carousel;
import com.zerodg.vwentity.entity.TestUser;
import com.zerodg.zdutil.util.JSONResult;
import com.zerodg.zdutil.util.Message;
import com.zerodg.zdutil.util.TestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * create by Administrator on 13:07 2019/6/25 0025
 */
@RestController
@Api(description = "显示首页轮播图")
@RequestMapping("/SY01")
@CrossOrigin
public class SY01Controller {

    @Autowired
    private SY01Service sy01Service;

    //首页的轮播图
    @ApiOperation(value="获取首页轮播图",notes="获取首页轮播图相关文章信息",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/slideshow",method= RequestMethod.GET)
    public JSONResult getCarouselList(){
        JSONResult<List<Carousel>> jsonResult=new JSONResult<>();

        List<Carousel> list =new ArrayList<>();
        list=sy01Service.getCarouselList();

        if(list!=null)
        {
            jsonResult.setData(list);
            jsonResult.setMessage(new Message("首页轮播图查找成功"));
            return jsonResult;
        }else {
            jsonResult.setMessage(new Message("首页轮播图查找为空"));
            return jsonResult;
        }
    }

    //首页的文章显示
    @ApiOperation(value="获取首页文章内容",notes="获取首页相关文章信息",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/article",method= RequestMethod.GET)
    public JSONResult getArticleList(){
        JSONResult<List<Article>> jsonResult=new JSONResult<>();
        List<Article> list =new ArrayList<>();
        list=sy01Service.getArticleList();
        if(list!=null)
        {
            jsonResult.setData(list);
            jsonResult.setMessage(new Message("首页文章内容查找成功"));
            return jsonResult;
        }else {
            jsonResult.setMessage(new Message("首页文章内容查找为空"));
            return jsonResult;
        }
    }

    //首页分类信息
    @ApiOperation(value="获取首页分类",notes="获取首页文章分类信息",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/sort",method= RequestMethod.GET)
    public JSONResult getSortList(){
        JSONResult<List<Article>> jsonResult=new JSONResult<>();

        List list =new ArrayList<>();
        list=sy01Service.getSortList();
        if(list!=null)
        {
            jsonResult.setData(list);
            jsonResult.setMessage(new Message("首页文章分类查找成功"));
            return jsonResult;
        }else {
            jsonResult.setMessage(new Message("首页文章分类查找为空"));
            return jsonResult;
        }
    }

}

package com.zerodg.vwapi.controller;

import com.zerodg.service.SY01Service;
import com.zerodg.vwentity.dto.SY.SY01ArticleDTO;
import com.zerodg.vwentity.dto.SY.SY01SlideshowDTO;
import com.zerodg.vwentity.dto.SY.SY01SortDTO;
import com.zerodg.vwentity.dto.SY.SY01LoveInputDTO.SY01LoveInputDTO;
import com.zerodg.vwentity.dto.SY.SY01Sort.SY01SortInputDTO;
import com.zerodg.vwentity.dto.SY.SY01TimeInputDTO.SY01TimeInputDTO;
import com.zerodg.vwentity.entity.Article;
import com.zerodg.zdutil.util.JSONResult;
import com.zerodg.zdutil.util.Message;
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
 * create by Administrator on 13:07 2019/6/25 0025
 * 首页
 */
@RestController
@Api(description = "显示首页")
@RequestMapping("/SY01")
@CrossOrigin
public class SY01Controller {

    @Autowired
    private SY01Service sy01Service;

    //首页的轮播图
    @ApiOperation(value="获取首页轮播图",notes="获取首页轮播图相关文章信息",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/slideshow",method= RequestMethod.GET)
    public JSONResult getCarouselList(){
        JSONResult<SY01SlideshowDTO> jsonResult=new JSONResult<>();

        //List<Carousel> list =new ArrayList<>();
        SY01SlideshowDTO list=new SY01SlideshowDTO();
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
    //////////////////////////////需要用户名
    @ApiOperation(value="获取首页文章内容",notes="获取首页相关文章信息",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/articleList",method= RequestMethod.GET)
    public JSONResult getArticleList(){
        JSONResult<SY01ArticleDTO> jsonResult=new JSONResult<>();
        SY01ArticleDTO list =new SY01ArticleDTO();
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
    @RequestMapping(value="/sortList",method= RequestMethod.GET)
    public JSONResult getSortList(){
        JSONResult<SY01SortDTO> jsonResult=new JSONResult<>();

        SY01SortDTO list =new SY01SortDTO();
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

    @ApiOperation(value="首页文章分类",notes="首页文章分类",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/sort",method= RequestMethod.GET)
    public  JSONResult login(@Valid SY01SortInputDTO input){

        JSONResult jsonResult=new JSONResult();

        List<Article> articleList=sy01Service.selectBySort(input.getSort());
        jsonResult.setData(articleList);
        return jsonResult;
    }

    @ApiOperation(value="首页右侧文章推荐（时间）",notes="首页右侧文章推荐（时间）",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/time",method= RequestMethod.GET)
    public JSONResult time(@Valid SY01TimeInputDTO input){

        JSONResult jsonResult=new JSONResult();
        List<Article> articleList=sy01Service.SelectArticleSortByTime();
        jsonResult.setData(articleList);

        return jsonResult;

    }

    @ApiOperation(value="首页右侧文章推荐（点赞）",notes="首页右侧文章推荐（点赞）",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/love",method= RequestMethod.GET)
    public JSONResult love(@Valid SY01LoveInputDTO input) {

        JSONResult jsonResult = new JSONResult();
        List<Article> articleList = sy01Service.SelectArticleSortByStar();
        jsonResult.setData(articleList);

        return jsonResult;

    }


}

package com.zerodg.vwapi.controller;

import com.zerodg.serviceImpl.PersonalServiceImpl;
import com.zerodg.vwentity.dto.Personal.GetMyArticlesDTO;
import com.zerodg.vwentity.dto.Personal.GetMyArticlesInputDTO;
import com.zerodg.zdutil.util.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: eroot
 * @description: 个人中心
 * @author: ztz-prince
 * @create:
 * @date:2019-10-25-16:03
 **/
@RestController
@Api(description = "个人中心")
@RequestMapping("/personalCenter")
@CrossOrigin
public class PersonalController {

    @Autowired
    private PersonalServiceImpl personalService;


    /**
     * 根据user id 获取发表的文章
     * @param input
     * @return
     */
    @ApiOperation(value="user id",notes="获取我的文章",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/getMyArticles",method= RequestMethod.GET)
    public JSONResult<List<GetMyArticlesDTO>> getMyArticles(@Valid GetMyArticlesInputDTO input){
        JSONResult<List<GetMyArticlesDTO>> jsonResult=new JSONResult<>();

        List<GetMyArticlesDTO> articleList = personalService.getMyArticlesByUserId(input.getUserId());

        jsonResult.setData(articleList);

        return jsonResult;
    }

    //根据文章 id 删除文章

}

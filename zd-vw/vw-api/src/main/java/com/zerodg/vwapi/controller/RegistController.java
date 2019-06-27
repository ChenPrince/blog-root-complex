package com.zerodg.vwapi.controller;


import com.zerodg.service.LoginService;
import com.zerodg.vwentity.dto.ZC01RegistInputDTO.ZC01RegistInputDTO;
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
import java.util.Date;

/**
 * create by  LZH on 2019/6/25
 */
@RestController
@Api(description = "注册页面")
@RequestMapping("/ZC01")
@CrossOrigin
public class RegistController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value="注册",notes="注册",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/regist",method= RequestMethod.GET)
    public JSONResult regist(@Valid ZC01RegistInputDTO input){

        JSONResult jsonResult=new JSONResult();
        String userName=input.getUserName();
        String userPassword=input.getPassword();
        String email=input.getEmail();
        System.out.println(userPassword);
       Integer r= loginService.regist(userName, userPassword,email);
        if(r==0){
            jsonResult.setMessage("此用户已存在，注册失败");
        }
        if(r==1){
            jsonResult.setMessage("注册成功");
        }

        return  jsonResult;


    }

}

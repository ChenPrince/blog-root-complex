package com.zerodg.vwapi.controller;


import com.zerodg.service.LoginService;
import com.zerodg.vwentity.dto.DL01LoginInputDTO.DL01LoginInputDTO;
import com.zerodg.vwentity.entity.User;
import com.zerodg.zdutil.util.JSONResult;
import com.zerodg.zdutil.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * create by  LZH on 2019/6/25
 */
@RestController
@Api(description = "登录页面")
@RequestMapping("/DL01")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;


    @ApiOperation(value="登录",notes="登录",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public JSONResult login(@Valid DL01LoginInputDTO input){
        //DL01LoginInputDTO
        System.out.println("登录");
        System.out.println(input.getPassword()+"--"+input.getUsername());
        JSONResult jsonResult=new JSONResult();
        String userName=input.getUsername();
        String userPassword=input.getPassword();
        User user=loginService.login(userName, userPassword);
        if(user!=null)
        {
            jsonResult.setMessage(new Message("登录成功"));
        }
        else{
            jsonResult.setMessage(new Message("登录失败"));
        }

        return jsonResult;
    }
}

package com.zerodg.vwapi.controller;

import com.zerodg.service.BS001Service;
import com.zerodg.service.UserService;
import com.zerodg.vwentity.dto.TempBS001.BS001A01InputDTO;
import com.zerodg.vwentity.entity.User;
import com.zerodg.vwentity.entity.WXUser;
import com.zerodg.zdutil.util.JSONResult;
import com.zerodg.zdutil.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @program: yj_root
 * @description: 未策划的临时接口
 * @author: ztz-prince
 * @create:
 * @date:2019-05-30-23:58
 **/

@RestController
@Api(description = "益家网页后台001")
@RequestMapping("/BS001")
@CrossOrigin
public class TempBS001Controller {

    private final BS001Service bs001Service;

    @Autowired
    public TempBS001Controller(BS001Service bs001Service){
        this.bs001Service=bs001Service;
    }


    @ApiOperation(value="用户List",notes="获取以注册的所有用户",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/A01",method= RequestMethod.POST)
    public JSONResult BS001A01(){

        JSONResult<List<WXUser>> jsonResult=new JSONResult<>();
        List<WXUser> userList=null;

        userList=bs001Service.getUserList();

        jsonResult.setData(userList);

        return jsonResult;

    }

    @ApiOperation(value="新增用户",notes="新增用户",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/A02",method= RequestMethod.POST)
    public JSONResult BS001A02(@RequestBody BS001A01InputDTO input){

        System.out.println(input.getAvatar()+""+input.getName()+"===="+input);
        JSONResult jsonResult=new JSONResult();

        WXUser wxUser=new WXUser();
        wxUser.setAvatar(input.getAvatar());
        wxUser.setLabel(input.getLabel());
        wxUser.setName(input.getName());
        wxUser.setOpenId(input.getOpenId());
        wxUser.setResume(input.getResume());
        wxUser.setSex(input.getSex());

        bs001Service.addNewWXUser(wxUser);

        jsonResult.setMessage(new Message("新增成功"));
        return jsonResult;
    }


}

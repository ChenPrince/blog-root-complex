package com.zerodg.vwapi.controller;



import com.zerodg.service.TestService;

import com.zerodg.vwentity.dto.Test.Test_UserInputDTO;
import com.zerodg.vwentity.entity.TestUser;
import com.zerodg.vwentity.entity.User;
import com.zerodg.zdutil.util.BeanMapper;
import com.zerodg.zdutil.util.JSONResult;
import com.zerodg.zdutil.util.TestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: swagger2demo
 * @description: 用户Controller
 * @author: ztz-prince
 * @create:
 * @date:2018-12-06-17:11
 **/

//一般来说接口统一都是传一个对象进来，所有的信息由前端分装在一个对象中，再转换为json传到后台
//所以在下列实例中，使用url传值的并不实用

//@Valid @RequestBody 数据验证
//RequestBody 只能用于post请求

@RestController
@Api(description = "测试")
@RequestMapping("/Test")
@CrossOrigin
public class TestController {
    //ConcurrentHashMap ,线程安全，能够接受高并发
    static Map<Long,TestUser> map=new ConcurrentHashMap<>();


    private final TestService testService;

    @Autowired
    public TestController(TestService testService){
        this.testService=testService;
    }

    /**
     * 获取用户列表
     * @return
     */
    @ApiOperation(value="获取用户列表",notes="获取所有用户信息",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/A01",method= RequestMethod.GET)
    public List<TestUser> getList(){
        List<TestUser> list =new ArrayList<>(map.values());
        return list;
    }

//    /**
//     * 创建用户
//     * @param user
//     * @return
//     */
//    //ui外部提示
//    @ApiOperation(value = "创建用户",notes="根据user对象创建用户",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    //ui内部提示
//    @ApiImplicitParam(name="user",value = "用户详情实体",required=true,dataType="TestUser")
//    @RequestMapping(value="/A02",method=RequestMethod.POST)
//    public String postTestUser( @Valid TestUser user){
//        System.out.println(user);
//        System.out.println(user.getId());
//
//        map.put(user.getId(),user);
//        String message=new String("添加成功");
//        return message;
//    }

    /**
     * @ @PathVariable注解：通过url获取值
     * @ @ApiImplicitParam：可以不用写，是用于增加ui提示
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户详情",notes = "根据url的id来获取用户基本信息")
//    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String",paramType = "path")
    @RequestMapping(value = "/A03/{id}",method = RequestMethod.GET)
    public TestUser getTestUserById(@PathVariable String id) {
        return  map.get(Long.parseLong(id));
    }



    @ApiOperation(value = "用户登陆测试", notes = "-", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = RequestMethod.GET, value = "/A04")
    public JSONResult userLogin(Test_UserInputDTO input){

        JSONResult jsonResult =new JSONResult();
        System.out.println("进来了");

        User user = testService.getUserById(1);
        System.out.println("==="+user.getName());

        //TODO
        //自动转换
        TestUser test_user= BeanMapper.map(input,TestUser.class);

        System.out.println(input.getName());
        System.out.println(input.getPassword());

        return jsonResult;
    }

    /**
     *
     * @param id
     * @param user
     * @return
     */
    @ApiOperation(value = "更新用户信息",notes = "根据url的id来指定对象，并且根据传过来的user进行用户基本信息更新")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "String"),
//            @ApiImplicitParam(name = "user", value = "用户详情实体类user", required = true, dataType = "TestUser")
//    })
    @RequestMapping(value = "/A04/{id}",method = RequestMethod.PUT)
    //@PathVariable 从url中获取参数
    public String putTestUser(@PathVariable String id,@RequestBody TestUser user) {
        Long ID=Long.parseLong(id);
        TestUser u = map.get(ID);
        u.setAge(user.getAge());
        u.setName(user.getName());
        map.put(ID,u);

        return "用户基本信息已经更新成功";

    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户",notes = "根据url的id来指定对象，进行用户信息删除")
//    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String",paramType = "path")
    @RequestMapping(value = "/A05/{id}",method = RequestMethod.DELETE)
    public String delTestUser(@PathVariable String id) {
        map.remove(Long.parseLong(id));
        return "用户ID为："+ id + " 的用户已经被移除系统";
    }

    // 用户插入测试
    @ApiOperation(value="SQL-user插入测试",notes = "插入数据")
//    @ApiImplicitParam(name="user",value="用户对象",required=true,dataType = "TestUser")
    @RequestMapping(value="/A06",method=RequestMethod.POST)
    public String userInsertTest(@RequestBody TestUser user){
        try {
//            testService.insertTestUser(user);
            return "用户插入成功";
        }
        catch (Exception e){
            return "用户插入失败";
        }
    }

    /**
     * 用户信息获取
     */
    @ApiOperation(value="SQL-user获取测试",notes="获取用户对象")
//    @ApiImplicitParam(name="id",value="用户id",required = true,dataType = "String")
    @RequestMapping(value="/A07",method=RequestMethod.GET)
    public JSONResult getTestUserTest(TestDTO input){
        JSONResult<TestUser>jsonResult=new JSONResult<>();
        try{
            TestUser user = testService.getTestUserById(input.getId());
            if(user==null){
                throw new Exception();
            }
            jsonResult.setData(user);
            return jsonResult;
        }
        catch (Exception e){
            jsonResult.setMessage("获取失败");
            return jsonResult;
        }
    }

}

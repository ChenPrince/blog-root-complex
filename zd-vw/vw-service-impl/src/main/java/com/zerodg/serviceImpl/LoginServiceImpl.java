package com.zerodg.serviceImpl;

import com.zerodg.service.LoginService;
import com.zerodg.vwdao.mapper.UserMapper;
import com.zerodg.vwentity.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * create by  LZH on 2019/6/25
 */
//@SuppressWarnings("ALL")
@Service
//全局事务
@Transactional
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String userName, String userPassword) {
        User user = userMapper.selectByName(userName);
        if (user != null) {
            if (user.getPassword().equals(userPassword)) {
                return user;
            }
        } else {
            return null;
        }

        return null;
    }

    @Override
    public Integer regist(String userName, String userPassword,String email) {

        List<User> users = userMapper.selectAll();
        Integer r = 1;

        for (User user : users) {
            if (user.getUserName().equals(userName)) {////如果数据库中存在此用户
                System.out.println("此用户已存在");
                r = 0;
                break;
            }
        }

        if (r == 1) {
            ///向数据库中插入数据
            userMapper.insertRegist(userName,userPassword,email);
            return  r;
        }
        else
        {
            return r;
        }

    }

}
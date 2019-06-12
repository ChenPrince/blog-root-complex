package com.zerodg.serviceImpl;


import com.zerodg.vwdao.mapper.UserMapper;
import com.zerodg.vwentity.entity.User;
import com.zerodg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * @program: demo
 * @description: 用户服务
 * @author: ztz-prince
 * @create:
 * @date:2018-12-12-19:32
 **/
@Component
@Transactional
//@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    public UserServiceImpl(UserMapper userMapper){
//        this.userMapper=userMapper;
//    }

    /**
     * 插入新用户
     */
    @Override
    public void insertUser(User user) {

        try{
            userMapper.insertNewUser(user);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public User getUserById(int id){
        User user=userMapper.getUserByID(id);
        return user;
    }

}

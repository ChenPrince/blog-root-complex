package com.zerodg.serviceImpl;

import com.zerodg.service.PHService;
import com.zerodg.vwdao.mapper.UserMapper;
import com.zerodg.vwentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by Administrator on 17:33 2019/6/25 0025
 */
@Service
public class PHServiceImpl implements PHService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer user_id) {
        User user=userMapper.selectByPrimaryKey(user_id);

        return user;
    }
}

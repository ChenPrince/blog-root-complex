package com.zerodg.serviceImpl;

import com.zerodg.service.TestService;
import com.zerodg.vwdao.mapper.UserMapper;
import com.zerodg.vwentity.entity.TestUser;
import com.zerodg.vwentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: qfsx_root
 * @description: 测试服务类
 * @author: ztz-prince
 * @create:
 * @date:2019-06-25-10:44
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {

        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public TestUser getTestUserById(Integer id) {
        return null;
    }
}

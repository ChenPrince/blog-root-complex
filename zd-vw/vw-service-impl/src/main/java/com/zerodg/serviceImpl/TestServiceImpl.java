package com.zerodg.serviceImpl;

import com.zerodg.service.TestService;
import com.zerodg.vwentity.entity.TestUser;
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
    @Override
    public TestUser getUserById(Integer id) {
        return null;
    }

    @Override
    public TestUser getTestUserById(Integer id) {
        return null;
    }
}

package com.zerodg.service;

import com.zerodg.vwentity.entity.TestUser;
import com.zerodg.vwentity.entity.User;

public interface TestService {
    User getUserById(Integer id);

    TestUser getTestUserById(Integer id);

}

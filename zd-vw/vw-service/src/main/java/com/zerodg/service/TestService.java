package com.zerodg.service;

import com.zerodg.vwentity.entity.TestUser;

public interface TestService {
    TestUser getUserById(Integer id);

    TestUser getTestUserById(Integer id);

}

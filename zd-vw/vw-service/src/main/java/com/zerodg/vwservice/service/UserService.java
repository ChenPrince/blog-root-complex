package com.zerodg.vwservice.service;

import com.zerodg.vwentity.entity.User;

/**
 * @program: eroot
 * @description: 用户服务接口
 * @author: ztz-prince
 * @create:
 * @date:2018-12-21-15:02
 **/
public interface UserService {

    void insertUser(User user);


    User getUserById(int id);

}

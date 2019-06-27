package com.zerodg.service;

import com.zerodg.vwentity.entity.User;

import java.util.Date;

public interface LoginService {
    User login(String userName, String userPassword);


    Integer regist(String userName, String userPassword,String email);
}

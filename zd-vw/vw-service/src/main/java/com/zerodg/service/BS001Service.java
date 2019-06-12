package com.zerodg.service;

import com.zerodg.vwentity.entity.WXUser;

import java.util.List;

/**
 * @program: yj_root
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-05-31-00:16
 **/
public interface BS001Service {

    List<WXUser> getUserList();

    void addNewWXUser(WXUser wxUser);

}

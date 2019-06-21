package com.zerodg.serviceImpl;

import com.zerodg.service.BS001Service;
import com.zerodg.vwdao.mapper.BS001Mapper;
import com.zerodg.vwentity.entity.WXUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: yj_root
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-05-31-00:17
 **/

@Component
//全局事务
@Transactional
public class BS001ServiceImpl implements BS001Service{

    @Autowired
    private BS001Mapper bs001Mapper;

    public List<WXUser> getUserList(){

        return bs001Mapper.getUserList();
    }

    public void addNewWXUser(WXUser wxUser){

        bs001Mapper.addNewWXUser(wxUser);
    }

}

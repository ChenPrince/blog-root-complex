package com.zerodg.vwdao.mapper;

import com.zerodg.vwentity.entity.WXUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: yj_root
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-05-31-00:21
 **/
public interface BS001Mapper {

    List<WXUser> getUserList();

    void addNewWXUser(WXUser wxUser);

}

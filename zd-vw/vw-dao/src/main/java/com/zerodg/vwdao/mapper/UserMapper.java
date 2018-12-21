package com.zerodg.vwdao.mapper;


import com.zerodg.vwentity.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @program: demo
 * @description: 用户sql映射
 * @author: ztz-prince
 * @create:
 * @date:2018-12-10-20:16
 **/
public interface UserMapper {

    /**
     * 插入新用户
     * @return
     */
    int insertNewUser(@Param("user") User user);

    /**
     * 通过用户id获取用户
     * @param id
     * @return
     */
    User getUserByID(int id);
}

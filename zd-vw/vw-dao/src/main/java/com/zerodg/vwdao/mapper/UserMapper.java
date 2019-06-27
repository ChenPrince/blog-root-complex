package com.zerodg.vwdao.mapper;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import com.zerodg.vwentity.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByName(String userName);


    Integer selectMaxId();


    void insertRegist( @Param("userName")String userName, @Param("userPassword")String userPassword,@Param("email")String email);
}
package com.zerodg.vwdao.mapper;

import com.zerodg.vwentity.entity.Tuser;
import java.util.List;

public interface TuserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Tuser record);

    Tuser selectByPrimaryKey(Integer userId);

    List<Tuser> selectAll();

    int updateByPrimaryKey(Tuser record);
}
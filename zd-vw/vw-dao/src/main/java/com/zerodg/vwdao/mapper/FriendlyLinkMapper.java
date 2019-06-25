package com.zerodg.vwdao.mapper;

import com.zerodg.vwentity.entity.FriendlyLink;
import java.util.List;

public interface FriendlyLinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FriendlyLink record);

    FriendlyLink selectByPrimaryKey(Integer id);

    List<FriendlyLink> selectAll();

    int updateByPrimaryKey(FriendlyLink record);
}
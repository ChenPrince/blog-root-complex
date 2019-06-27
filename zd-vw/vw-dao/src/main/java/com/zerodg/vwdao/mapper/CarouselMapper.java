package com.zerodg.vwdao.mapper;

import com.zerodg.vwentity.entity.Carousel;
import java.util.List;

public interface CarouselMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Carousel record);

    Carousel selectByPrimaryKey(Integer id);

    List<Carousel> selectAll();

    int updateByPrimaryKey(Carousel record);

    List<Carousel> selectByIs_select();
}
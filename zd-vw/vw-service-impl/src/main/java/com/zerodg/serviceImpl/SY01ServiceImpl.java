package com.zerodg.serviceImpl;

import com.zerodg.service.SY01Service;
import com.zerodg.vwdao.mapper.ArticleMapper;
import com.zerodg.vwdao.mapper.CarouselMapper;
import com.zerodg.vwentity.entity.Article;
import com.zerodg.vwentity.entity.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by Administrator on 13:10 2019/6/25 0025
 */
@Service
public class SY01ServiceImpl implements SY01Service {

    @Autowired
    private CarouselMapper carouselMapper;

    @Autowired
    private ArticleMapper articleMapper;

    //通过判断is_select输入相应的轮播图的信息
    @Override
    public List<Carousel> getCarouselList() {
        List<Carousel> list=carouselMapper.selectByIs_select();
        System.out.println("jg:"+list);

        return list;
    }

    //显示所有的文章信息
    @Override
    public List<Article> getArticleList() {
        List<Article> lists=articleMapper.selectAll();

        //对文章内容进行处理
        for (Article list : lists) {
            if(list.getContent().length()>100)
            {
                list.setContent(list.getContent().substring(0,100)+"....");
            }
        }

        return lists;
    }

    //显示首页分类信息
    @Override
    public List getSortList() {
        List list=articleMapper.selectSort();
        return list;
    }
}

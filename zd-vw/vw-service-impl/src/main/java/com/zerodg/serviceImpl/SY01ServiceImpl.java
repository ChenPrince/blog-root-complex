package com.zerodg.serviceImpl;

import com.zerodg.service.SY01Service;
import com.zerodg.vwdao.mapper.ArticleMapper;
import com.zerodg.vwdao.mapper.CarouselMapper;
import com.zerodg.vwdao.mapper.UserMapper;
import com.zerodg.vwentity.dto.SY.SY01ArticleDTO;
import com.zerodg.vwentity.dto.SY.SY01SlideshowDTO;
import com.zerodg.vwentity.dto.SY.SY01SortDTO;
import com.zerodg.vwentity.entity.Article;
import com.zerodg.vwentity.entity.Carousel;
import com.zerodg.vwentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    private UserMapper userMapper;

    //通过判断is_select输入相应的轮播图的信息
    @Override
    public SY01SlideshowDTO getCarouselList() {
        SY01SlideshowDTO list=new SY01SlideshowDTO();
        List<Carousel> carouselList = carouselMapper.selectByIs_select();
        /*for (Carousel carousel : carouselList) {
            SY01SlideshowDTO sy01SlideshowDTO = new SY01SlideshowDTO();
            sy01SlideshowDTO.setId(carousel.getId());
            sy01SlideshowDTO.setArticleId(carousel.getArticleId());
            sy01SlideshowDTO.setCreateAt(carousel.getCreateAt());
            sy01SlideshowDTO.setTitle(carousel.getTitle());
            sy01SlideshowDTO.setIsSelect(carousel.getIsSelect());
            list.add(sy01SlideshowDTO);
        }*/
        for (Carousel carousel : carouselList) {
            //处理时间
        }
        //System.out.println("jg:"+list);
        return list;
    }

    //显示所有的文章信息
    @Override
    public SY01ArticleDTO getArticleList() {
        SY01ArticleDTO lists=new SY01ArticleDTO();
        List<Article> Articlelists=articleMapper.selectAll();


        for (Article articlelist : Articlelists) {
            //对文章内容进行处理
            if (articlelist.getContent().length() > 100) {
                articlelist.setContent(articlelist.getContent().substring(0, 100) + "....");
            }
            //添加用户名
            User user = userMapper.selectByPrimaryKey(articlelist.getUserId());
            articlelist.setUserName(user.getUserName());

            //处理时间
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
            String str=sf.format(articlelist.getCreateAt());
            articlelist.setCreateTime(str);
        }
        lists.setArticleList(Articlelists);

        /*for (Article list : Articlelist) {
            if(list.getContent().length()>100)
            {
                list.setContent(list.getContent().substring(0,100)+"....");
            }
            SY01ArticleDTO sy01ArticleDTO = new SY01ArticleDTO();
            sy01ArticleDTO.setId(list.getId());
            sy01ArticleDTO.setContent(list.getContent());
            sy01ArticleDTO.setCreateAt(list.getCreateAt());
            sy01ArticleDTO.setDiss(list.getDiss());
            sy01ArticleDTO.setSort(list.getSort());
            sy01ArticleDTO.setStar(list.getStar());
            sy01ArticleDTO.setUserId(list.getUserId());
            sy01ArticleDTO.setVisit(list.getVisit());
            sy01ArticleDTO.setTitle(list.getTitle());
            lists.add(sy01ArticleDTO);
        }*/

        return lists;
    }

    //显示首页分类信息
    @Override
    public SY01SortDTO getSortList() {
        SY01SortDTO lists=new SY01SortDTO();

        List<Article> articlelists=articleMapper.selectSort();
        List<String> stringList=new ArrayList<>();
        for (Article articlelist : articlelists) {
            //SY01SortDTO sy01SortDTO = new SY01SortDTO();
            //sy01SortDTO.setSort(articlelist.getSort());
            //lists.add(sy01SortDTO);
            stringList.add(articlelist.getSort());
        }
        /*List<String> articleString=articleMapper.selectSort();
        articleString.*/
        lists.setListSort(stringList);
        return lists;
    }
}

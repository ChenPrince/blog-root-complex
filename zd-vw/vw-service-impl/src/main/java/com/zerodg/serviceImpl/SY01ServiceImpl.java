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

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * create by Administrator on 13:10 2019/6/25 0025
 */
@Service
public class SY01ServiceImpl implements SY01Service {

    @Resource
    private CarouselMapper carouselMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private UserMapper userMapper;

    //通过判断is_select输入相应的轮播图的信息
    @Override
    public SY01SlideshowDTO getCarouselList() {
        SY01SlideshowDTO list=new SY01SlideshowDTO();
        List<Carousel> carouselList = carouselMapper.selectByIs_select();

        for (Carousel carousel : carouselList) {
            //处理时间
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
            String str=sf.format(carousel.getCreateAt());
            carousel.setCreateTime(str);
        }
        list.setArticlefousList(carouselList);
        list.setCarouseList(carouselList);
        return list;
    }

    //显示所有的文章信息
    @Override
    public SY01ArticleDTO getArticleList() {
        SY01ArticleDTO lists=new SY01ArticleDTO();
        List<Article> articleLists=articleMapper.selectAll();


        for (Article article : articleLists) {

            //去掉标签
            String htmlRegex="<[^>]+>";
            String content=article.getContent().replaceAll(htmlRegex,"");
            System.out.println(content);
            article.setContent(content);
            //对文章内容进行处理
//            if (article.getContent().length() >60 ) {
//                article.setContent(article.getContent().substring(0, 60) + "....");
//            }
            if (content.length() >60 ) {
                article.setContent(content.substring(0, 60) + "....");
            }
            //添加用户名
            User user = userMapper.selectByPrimaryKey(article.getUserId());
            article.setUserName(user.getUserName());

            //处理时间
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
            String str=sf.format(article.getCreateAt());
            article.setCreateTime(str);
        }
        lists.setArticleList(articleLists);


        return lists;
    }

    //显示首页分类信息
    @Override
    public SY01SortDTO getSortList() {
        SY01SortDTO lists=new SY01SortDTO();

        List<Article> articleLists=articleMapper.selectSort();
        List<String> stringList=new ArrayList<>();
        for (Article articleList : articleLists) {

            stringList.add(articleList.getSort());
        }

        lists.setListSort(stringList);
        return lists;
    }

    /**
     * 点击分类获取文章
     *
     * @param sort
     * @return
     */

    @Override
    public List<Article> selectBySort(String sort) {

        List<Article> articleLists = articleMapper.selectBySort(sort);
        for (Article articleList : articleLists) {
            //对文章内容进行处理
            if (articleList.getContent().length() > 25) {
                articleList.setContent(articleList.getContent().substring(0, 25) + "....");
            }
        }
        return articleLists;
    }
    /**
     * 通过时间排序
     *
     * @return
     */
    @Override
    public List<Article> SelectArticleSortByTime() {

        List<Article> articleList = articleMapper.selectArticleByTime();
        List<Article> articles = new ArrayList<>();

        Integer count = 5, i = 0;
        for (Article article : articleList) {
            if (i < count) {
                articles.add(article);
                System.out.println(article.getId());
            }
            i++;
        }
        return articles;
    }
    /**
     * 通过点赞排序
     *
     * @return
     */
    @Override
    public List<Article> SelectArticleSortByStar() {
        List<Article> articleList = articleMapper.selectArticleByStar();
        List<Article> articles = new ArrayList<>();

        Integer count = 5, i = 0;
        for (Article article : articleList) {
            if (i < count) {
                articles.add(article);
            }
            i++;
        }
        return articles;
    }


}

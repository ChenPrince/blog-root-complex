package com.zerodg.serviceImpl;

import com.zerodg.service.PersonalService;
import com.zerodg.vwdao.mapper.ArticleMapper;
import com.zerodg.vwentity.dto.Personal.GetMyArticlesDTO;
import com.zerodg.vwentity.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: eroot
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-10-25-17:45
 **/
@Service
//全局事务
@Transactional
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<GetMyArticlesDTO> getMyArticlesByUserId(Integer userId) {


        List<Article> articleList = articleMapper.selectArticleByUserId(userId);

        List<GetMyArticlesDTO> resultList=new LinkedList<>();

        for(int i=0;i<articleList.size();i++){
            Article article=articleList.get(i);
            GetMyArticlesDTO box=new GetMyArticlesDTO();
            box.setArticleId(article.getId());
            box.setArticleTitle(article.getTitle());

            String htmlRegex="<[^>]+>";
            String content=article.getContent().replaceAll(htmlRegex,"");
            content=content.length()>30?content.substring(0,30)+"...":content;
            box.setArticleAbstract(content);

            resultList.add(box);
        }

        //处理内容

        return resultList;
    }
}

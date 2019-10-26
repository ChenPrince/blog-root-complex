package com.zerodg.service;

import com.zerodg.vwentity.dto.Personal.GetMyArticlesDTO;

import java.util.List;

public interface PersonalService {

    List<GetMyArticlesDTO> getMyArticlesByUserId(Integer userId);

}

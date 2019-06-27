package com.zerodg.vwentity.dto.Article;

import com.zerodg.vwentity.entity.Comment;

import java.util.Date;
import java.util.List;

public class ArticleCommentDTO {
    private List<Comment> commentList;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}

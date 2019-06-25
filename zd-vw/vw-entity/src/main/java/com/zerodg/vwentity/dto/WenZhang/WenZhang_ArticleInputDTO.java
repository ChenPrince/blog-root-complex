package com.zerodg.vwentity.dto.WenZhang;

import java.util.Date;

/*
 *create by loser on 2019/6/25
 */
public class WenZhang_ArticleInputDTO {
    private Integer id;

    private Integer user_id;

    private Integer star;

    private Integer visit;

    private String content;

    private String sort;

    private Date create_at;

    private Integer diss;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId(){return user_id;}

    public void setUserId(Integer user_id){this.user_id = user_id;}

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getSort() { return sort; }

    public void setSort(String sort) { this.sort = sort; }

    public Date getCreate_at() { return create_at; }

    public void setCreate_at(Date create_at) { this.create_at = create_at; }

    public Integer getDiss() { return diss; }

    public void setDiss(Integer diss) { this.diss = diss; }






}

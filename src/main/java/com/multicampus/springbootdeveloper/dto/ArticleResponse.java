package com.multicampus.springbootdeveloper.dto;

import com.multicampus.springbootdeveloper.domain.Article;
import lombok.Getter;

@Getter //목록 조회 결과물을 저장할 DTO
public class ArticleResponse {
    private String title;
    private String content;

    public ArticleResponse(Article article){
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}

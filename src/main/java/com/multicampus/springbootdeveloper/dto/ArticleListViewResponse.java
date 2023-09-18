package com.multicampus.springbootdeveloper.dto;

import lombok.Getter;
import com.multicampus.springbootdeveloper.domain.Article;
import lombok.NoArgsConstructor;

@Getter
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}

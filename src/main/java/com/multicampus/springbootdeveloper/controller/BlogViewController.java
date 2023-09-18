package com.multicampus.springbootdeveloper.controller;

import com.multicampus.springbootdeveloper.domain.Article;
import com.multicampus.springbootdeveloper.dto.ArticleListViewResponse;
import com.multicampus.springbootdeveloper.dto.ArticleViewResponse;
import com.multicampus.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//blog article all list : /articles GET 요청을 처리한다. 블로그 글 전체 리스트를 담은 뷰 반환
@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticle(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream().map(ArticleListViewResponse::new).toList();
        model.addAttribute("articles",articles);
        return "articleList";   //articleList.html 뷰 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    //수정
    @GetMapping("/new-article")
    //id를 가진 쿼리 파라미터의 값을 id변수에 매핑
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null){
            model.addAttribute("article",new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }




}

















package com.multicampus.springbootdeveloper.dto;

import com.multicampus.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor  //기본 생성자 추가
@AllArgsConstructor  //모든 필드 값을 파라미터로 받는 생성자를 추가
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity(){  //생성자를 이용해서 객체 생성한다. toEntity()메서드는 빌더패턴을 이용해서 DTO를 엔티티로 만들어주는 기능
        //블로그 글을 추가할때 저장할 엔티티로 변환하는 용도
        return Article.builder().title(title).content(content).build();
    }

}

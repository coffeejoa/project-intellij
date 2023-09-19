package com.multicampus.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @CreatedDate  // 엔티티가 생성될때 생성 시간 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate //엔티티가 수정될때 수정 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder  //빌터패턴을 이용해서 객체 생성  : 어느 필드에 어떤값이 들어가는지 명시적으로 파악할 수 있다
    public Article(String title,String content){
        this.title = title;
        this.content = content;
    }

   /*  new Article("adc","aaaaaa");  빌터 패턴을 적용하지 않고 객체 생성

     Article.builder().title("abc").content("def"),build();  빌더 패턴 적용 */

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

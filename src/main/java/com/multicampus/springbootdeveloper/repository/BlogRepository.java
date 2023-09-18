package com.multicampus.springbootdeveloper.repository;

import com.multicampus.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Long> {
}

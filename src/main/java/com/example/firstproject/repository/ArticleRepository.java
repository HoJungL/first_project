package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> { // Article : 관리 대상 엔티티의 클래스 타입, Long : 관리 대상 엔티티의 대푯값 타입.
    @Override
    ArrayList<Article> findAll();
}

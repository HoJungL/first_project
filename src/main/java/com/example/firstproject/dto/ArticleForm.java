package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleForm {

    private Long id;
    private String title;
    private String content;
    // 마우스 우클릭 후 Generate -> Constructor 클릭하면 아래의 코드가 나옴.
    public ArticleForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
     // 마우스 우클릭 후 Generate -> toString() 클릭하면 아래의 코드가 나옴.
    @Override
    public String toString() {
        return "ArticleForm{" + id +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(id, title, content);
    }
}

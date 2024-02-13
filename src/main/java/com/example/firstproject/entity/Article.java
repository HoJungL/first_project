package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity // 1. 엔티티 선언
public class Article {
    @Id // 3. 엔티티의 대표값 지정
//    3. 자동 생성 기능 추가 (숫자 자동 매기기)
    // strategy = GenerationType.IDENTITY 하면 알아서..
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;
    @Column // 2. title 필드 선언, DB table의 title 열과 연결
    private String title;
    @Column // 2. content 필드 선언, DB table의 content 열과 연결
    private String content;
    Article() { // 이거 대신 롬복 @NoArgsConstructor로 할수 있음. 기본 생성자 추가 어노테이션임.

    }
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }



    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;

        if (article.content != null)
            this.content = article.content;
    }
}


package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Entity // 1. 엔티티 선언
public class Article_lombok {
    @Id // 3. 엔티티의 대표값 지정
    @GeneratedValue // 3. 자동 생성 기능 추가 (숫자 자동 매기기)
    private Long id;
    @Column // 2. title 필드 선언, DB table의 title 열과 연결
    private String title;
    @Column // 2. content 필드 선언, DB table의 content 열과 연결
    private String content;
}


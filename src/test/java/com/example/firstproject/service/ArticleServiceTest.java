package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 부트와 연동해 테스트
class ArticleServiceTest {

    @Autowired
    ArticleService articleService; // articleService 객체 주입
    @Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L, "aaaa", "1111");
        Article b = new Article(2L, "bbbb", "2222");
        Article c = new Article(3L, "cccc", "3333");
        // Arrays.asList 메서드 : 입력된 배열 또는 2개 이상의 동일한 타입 데이터를 정적 리스트로 만들어 반환함.
        // 정적 리스트는 고정 size이기 때문에, add, remove 못씀...
        // 만약 쓸거면 정정 리스트를 일반 리스트로 바꿔야함.
        // List<String> list = new ArrayList<>(fixedSzieListA) 이런식으로
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        // 2. 실제 데이터
        List<Article> articles = articleService.index();

        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString()); // 기대값, 실제값
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "aaaa", "1111");

        // 2. 실제 데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
        
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected, article); // null은 toString을 못받아연
    }

    @Test
    @Transactional
    void create_성공_title과_content만있는_dto_입력() {
        // 1. 예상데이터
        String title = "dddd";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상데이터
        Long id = 4L;
        String title = "dddd";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null; // 이거 안하면 실패뜸!! 왜냐하면 ID 가 없어야하는데, 있다고 지금 내가 선언을 했으니까!!

        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected, article);

    }
}
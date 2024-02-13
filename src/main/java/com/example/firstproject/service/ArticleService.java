package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() { //오른쪽 마우스 -> generate -> test -> 할거 check 하면 java 파일 하나 생김
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        //1. DTO -> 엔티티 변환
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        //2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null; // 응답은 컨트롤러가 하니까 여기서는 null
        }
        // 4. 업데이트 하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated; // 응답은 컨트롤러가 하므로, 여기서는 수정 데이터만 반환
    }

    public Article deleted(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리
        if (target == null) {
            return null;
        }
        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return target; // DB에서 삭제한 대상을 컨트롤러에 반환
    }

    /*스트림 문법1
    리스트와 같은 자료구조에 저장도니 요소를 하나씩 순회하면서 처리하는 코드 패턴.
    */
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream() // dto 스트림화하기
                .map(dto -> dto.toEntity()) //dto 하나 올때마다 하나씩 엔티티로 변환
                .collect(Collectors.toList());// 리스트로 묶어야징
        
        // 2. 엔티티 묶음을 dB에 저장
        articleList.stream() // 스트림화 하셈
                .forEach(article -> articleRepository.save(article)); // 리포지토리를 통해 DB에 저장
        
        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L) // id가 -1인 데이터 찾기, L은 Long 단위에서 쓰임
                .orElseThrow(() -> new IllegalArgumentException("결제 실패"));
        // orElseThrow : 존재하면 값 반환, 없으면 예외 발생 찾는 데이터가 없으면 예외 발생

        // 4. 결과 값 반환하기
        return articleList;
    }
}

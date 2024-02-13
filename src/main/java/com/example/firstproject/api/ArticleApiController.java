package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //Simple Logging Facade for Java
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    //Get
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    //Post
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) { // 반환형 수정
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.deleted(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //트랜잭션을 만들어 보아요
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
/*{
    @Autowired // 게시글 리파지터리 주입!!!
    private ArticleRepository articleRepository;

    // get
    @GetMapping("/api/articles") // url 요청 접수
    public List<Article> index() {  // index 메서드 정의
        return articleRepository.findAll();
    }

    // get에서 단일 게시글 조회하기.
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) { //RequestBody 없으면 id만 가져와버림
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, // 반환형 수정 -- ResponseEntity<>
                                          @RequestBody ArticleForm dto) {
        // 1. dto -> 엔티티 변환
        Article article = dto.toEntity(); // dto 를 엔티티로!
        log.info("id : {}, article: {}", id, article.toString());

        // 2. 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리
        if (target == null || id != article.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // responseEntity 반환
        }
        // 4. 업데이트 및 정상응답(200) 하기
        target.patch(article); // 기존 데이터에 새데이터(갱신된) 붙이셈
        Article updated = articleRepository.save(target); // 갱신된거 불러오셈.
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null); // body(null) 대산 build() 써도 무관
    }
}*/

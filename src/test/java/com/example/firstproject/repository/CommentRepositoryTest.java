package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest //jpa와 연동해서 테스팅
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository; // 객체 주입

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1 : 4번 게시글의 모든 댓글 조회*/
        {
            // 1. 입력데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(4L, "your best movie?", "go");
            Comment a = new Comment(1L, article, "Park", "good will hunting");
            Comment b = new Comment(2L, article, "Kim", "i am sam");
            Comment c = new Comment(3L, article, "Choi", "escape");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력.");
        }
        /* Case 2 : 1번 게시글의 모든 댓글 조회*/
        {
            // 1. 입력데이터 준비
            Long articleId = 1L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(1L, "aaaa", "1111");
            List<Comment> expected = Arrays.asList();

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글 없음.");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: "Park"의 모든 댓글 조회*/
        {
            //1. 입력데이터 준비
            String nickname = "Park";
            //2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            //3. 예상 데이터
            Comment a = new Comment(1L, new Article(4L, "your best movie?", "go"), nickname, "good will hunting");
            Comment b = new Comment(4L, new Article(5L, "your soul food?", "gogo"), nickname, "chicken");
            Comment c = new Comment(7L, new Article(6L, "your hobby?", "gogogo"), nickname, "jogging");
            List<Comment> expected = Arrays.asList(a, b, c);
            
            //4. 비교및 검증
            assertEquals(expected.toString(), comments.toString(),"Park의 모든 댓글 출력");

        }
    }
}
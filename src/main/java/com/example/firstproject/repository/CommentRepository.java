package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 쿼리를 메소드로 작성하는 것을 네이티브 쿼리 메서드라고 함! (native query method)
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특정 게시글의 모든 댓글 조회 , Query 이노테이션 방법
    @Query(value = "select * from comment where article_id = :articleId", // where절에서 매개변수에 :를 안붙이면 매칭이 안되여!
    nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    //특정 닉네임의 모든 댓글 조회 , XML (natvie query XML)로 조회 방법 orm.xml , 이 방법은 META-INF로 써야함!!!
    List<Comment> findByNickname(String nickname);


}

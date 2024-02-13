package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 해당 클래스 엔티티 선언, 클래스 필드 바탕 DB 테이블 생성
@Getter // 각 필드 값 조회할수 있는 getter 메서드 자동 생성
@ToString // 모든 필드 출력할수 있는 to String 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long id; //대표키
    @ManyToOne
    @JoinColumn(name="article_id") // Article 엔티티의 기본키와 매핑
    private Article article; // 댓글의 부모 게시글
    @Column
    private String nickname; // 댓글 단놈
    @Column
    private String body; // 댓글 본문

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야합니다.");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id == dto.getId()) // this : 기존의 댓글
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 ID가 입력됐습니다.");
        // 객체 갱신
        if (dto.getNickname() != null) // 수정할 닉넴 잇으면
            this.nickname = dto.getNickname(); // 내용 반영
        if (dto.getBody() != null) // 수정할 내용 있으면
            this.body = dto.getBody(); // 내용 반영
    }
}

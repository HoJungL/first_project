package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 선언
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository; // 댓글 리퍼지터리 객체 주입

    @Autowired
    private ArticleRepository articleRepository; // 게시글 리파지터리 객체 주입

    public List<CommentDto> comments(Long articleId) {
/*
        // 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔티티 -> dto 반환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++) { // 조회한 댓글 엔티티 수만큼 반복
            Comment c = comments.get(i); // 조회한 댓글 엔티티 하나씩 가져오기
            CommentDto dto = CommentDto.createCommentDto(c); // 엔티티를 DTO로 반환
            dtos.add(dto); // 변환한 DTO를 dtos 리스트에 삽임
        }
*/
        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    public CommentDto create(Long articleId, CommentDto dto) {
        
        // 1.게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId) // 부모 게시글 가져오고
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!" + "대상게시글이 없습니다.")); // 없으면 에러 메세지
        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        
        // 4. DTO를 변환해 반환
        return CommentDto.createCommentDto(created);
    }
    
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" + "대상 댓글이 없습니다."));
        // 2. 댓글 수정
        target.patch(dto);
        // 3. dB로 갱신
        Comment updated = commentRepository.save(target);
        // 4. 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
        // 2. 댓글 삭제
        commentRepository.delete(target);
        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);

    }
}

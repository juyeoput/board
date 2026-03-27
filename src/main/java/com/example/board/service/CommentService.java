package com.example.board.service;

import com.example.board.dto.CommentRequestDto;
import com.example.board.dto.CommentResponseDto;
import com.example.board.entity.Comment;
import com.example.board.entity.Member;
import com.example.board.entity.Post;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.MemberRepository;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long create(Long postId, String username, CommentRequestDto requestDto) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Member not found."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found."));

        Comment comment = new Comment(requestDto.getContent(), member, post);
        commentRepository.save(comment);
        return comment.getId();
    }

    public List<CommentResponseDto> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long commentId, String username, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found."));

        if (!comment.getMember().getUsername().equals(username)) {
            throw new IllegalArgumentException("Unauthorized access.");
        }

        comment.update(requestDto.getContent());
        return comment.getId();
    }

    @Transactional
    public void delete(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found."));

        if (!comment.getMember().getUsername().equals(username)) {
            throw new IllegalArgumentException("Unauthorized access.");
        }

        commentRepository.delete(comment);
    }
}
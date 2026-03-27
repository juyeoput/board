package com.example.board.controller;

import com.example.board.dto.CommentRequestDto;
import com.example.board.dto.CommentResponseDto;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Long> create(
            @PathVariable Long postId,
            @AuthenticationPrincipal String username,
            @RequestBody CommentRequestDto requestDto) {
        return ResponseEntity.ok(commentService.create(postId, username, requestDto));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.findByPostId(postId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Long> update(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal String username,
            @RequestBody CommentRequestDto requestDto) {
        return ResponseEntity.ok(commentService.update(commentId, username, requestDto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal String username) {
        commentService.delete(commentId, username);
        return ResponseEntity.noContent().build(); // 204 No Content - no response body needed for deletion
    }
}
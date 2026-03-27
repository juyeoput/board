package com.example.board.controller;

import com.example.board.dto.PostRequestDto;
import com.example.board.dto.PostResponseDto;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Long> create(
            @AuthenticationPrincipal String username,
            @RequestBody PostRequestDto requestDto) {
        return ResponseEntity.ok(postService.create(username, requestDto));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(
            @PathVariable Long id,
            @AuthenticationPrincipal String username,
            @RequestBody PostRequestDto requestDto) {
        return ResponseEntity.ok(postService.update(id, username, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal String username) {
        postService.delete(id, username);
        return ResponseEntity.noContent().build(); // 204 No Content - no response body needed for deletion
    }
}
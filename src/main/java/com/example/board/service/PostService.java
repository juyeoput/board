package com.example.board.service;

import com.example.board.dto.PostRequestDto;
import com.example.board.dto.PostResponseDto;
import com.example.board.entity.Member;
import com.example.board.entity.Post;
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
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(String username, PostRequestDto requestDto) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Member not found."));

        Post post = new Post(requestDto.getTitle(), requestDto.getContent(), member);
        postRepository.save(post);
        return post.getId();
    }

    public List<PostResponseDto> findAll() {
        return postRepository.findAll().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    public PostResponseDto findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found."));
        return new PostResponseDto(post);
    }

    @Transactional
    public Long update(Long id, String username, PostRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found."));

        if (!post.getMember().getUsername().equals(username)) {
            throw new IllegalArgumentException("Unauthorized access.");
        }

        post.update(requestDto.getTitle(), requestDto.getContent());
        return post.getId();
    }

    @Transactional
    public void delete(Long id, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found."));

        if (!post.getMember().getUsername().equals(username)) {
            throw new IllegalArgumentException("Unauthorized access.");
        }

        postRepository.delete(post);
    }
}
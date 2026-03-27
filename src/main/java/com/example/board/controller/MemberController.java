package com.example.board.controller;

import com.example.board.dto.MemberRequestDto;
import com.example.board.dto.MemberResponseDto;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")

    public ResponseEntity<String> login(@RequestBody MemberRequestDto requestDto) {
        String token = memberService.login(
                requestDto.getUsername(),
                requestDto.getPassword()
        );
        return ResponseEntity.ok(token);
    }

    @PostMapping("/join")
    public ResponseEntity<Long> join(@RequestBody MemberRequestDto requestDto) {
        Long memberId = memberService.join(
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getEmail()
        );

        return ResponseEntity.ok(memberId);
    }
}
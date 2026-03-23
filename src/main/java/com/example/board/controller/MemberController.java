package com.example.board.controller;

import com.example.board.dto.MemberRequestDto;
import com.example.board.dto.MemberResponseDto;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // @Controller + @ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<Long> join(@RequestBody MemberRequestDto requestDto) { // 클라이언트가 보낸 JSON 데이터를 MemberRequestDto 객체로 자동 변환
        Long memberId = memberService.join(
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getEmail()
        );

        return ResponseEntity.ok(memberId);
    }
}
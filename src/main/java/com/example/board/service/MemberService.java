package com.example.board.service;

import com.example.board.entity.Member;
import com.example.board.repository.MemberRepository;
import com.example.board.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public Long join(String username, String password, String email) {
        if (memberRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        Member member = new Member(username, encodedPassword, email);
        memberRepository.save(member);
        return member.getId();
    }

    public String login(String username, String password) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Member not found."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("Invalid password.");
        }

        return jwtUtil.generateToken(username);
    }
}
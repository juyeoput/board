package com.example.board.repository;

import com.example.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //회원 중복 체크
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
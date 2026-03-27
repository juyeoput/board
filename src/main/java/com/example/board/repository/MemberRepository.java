package com.example.board.repository;

import com.example.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<Member> findByUsername(String username);
}
package com.example.dormitorybe.repository;

import com.example.dormitorybe.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

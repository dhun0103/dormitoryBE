package com.example.dormitorybe.repository;

import com.example.dormitorybe.domain.Member;
import com.example.dormitorybe.domain.NoticePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticePostRepository extends JpaRepository<NoticePost, Long> {
}

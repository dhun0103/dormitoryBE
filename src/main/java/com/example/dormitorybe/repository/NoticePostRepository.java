package com.example.dormitorybe.repository;

import com.example.dormitorybe.domain.NoticePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticePostRepository extends JpaRepository<NoticePost, Long> {
}

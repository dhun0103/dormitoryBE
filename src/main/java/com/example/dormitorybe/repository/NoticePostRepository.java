package com.example.dormitorybe.repository;

import com.example.dormitorybe.domain.Member;
import com.example.dormitorybe.domain.NoticePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NoticePostRepository extends JpaRepository<NoticePost, Long> {
    @Query(value = "SELECT * FROM notice_post ORDER BY notice_post_id DESC LIMIT 3", nativeQuery = true)
    List<NoticePost> findLatestThreeNoticePosts();
    Optional<Member> findBy();
}

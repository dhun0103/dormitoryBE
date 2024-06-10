package com.example.dormitorybe.repository;

import com.example.dormitorybe.entity.ChatRoomJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomJpaEntity, Long> {
    Slice<ChatRoomJpaEntity> findAllBy(Pageable pageable);
}
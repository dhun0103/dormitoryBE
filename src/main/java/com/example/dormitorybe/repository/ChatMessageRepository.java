package com.example.dormitorybe.repository;

import com.example.dormitorybe.entity.ChatMessageJpaEntity;
import com.example.dormitorybe.entity.ChatRoomJpaEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessageJpaEntity, Long> {
    Optional<ChatRoomJpaEntity> findByChatRoomChatRoomId(Long id);
    Slice<ChatMessageJpaEntity> findAllByChatRoom(ChatRoomJpaEntity chatRoomJpaEntity, PageRequest pageRequest);
}
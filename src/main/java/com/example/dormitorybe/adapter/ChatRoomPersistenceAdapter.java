package com.example.dormitorybe.adapter;

import com.example.dormitorybe.common.annotation.PersistenceAdapter;
import com.example.dormitorybe.domain.ChatRoom;
import com.example.dormitorybe.entity.ChatRoomJpaEntity;
import com.example.dormitorybe.port.out.CreateChatRoomPort;
import com.example.dormitorybe.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class ChatRoomPersistenceAdapter implements CreateChatRoomPort {
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    @Override
    public boolean createChatRoom(ChatRoom chatRoom) {
        ChatRoomJpaEntity chatRoomJpaEntity = ChatRoomJpaEntity.builder()
                .build();
        chatRoomRepository.save(chatRoomJpaEntity);
        return true;
    }
}
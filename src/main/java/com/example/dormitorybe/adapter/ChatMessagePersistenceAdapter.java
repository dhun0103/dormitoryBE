package com.example.dormitorybe.adapter;

import com.example.dormitorybe.entity.ChatMessageJpaEntity;
import com.example.dormitorybe.entity.ChatRoomJpaEntity;
import com.example.dormitorybe.port.out.CreateChatMessagePort;
import com.example.dormitorybe.domain.ChatMessage;
import com.example.dormitorybe.common.annotation.PersistenceAdapter;
import com.example.dormitorybe.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
class ChatMessagePersistenceAdapter implements CreateChatMessagePort {
    private final ChatRoomRepository chatRoomPostRepository;

    @Override
    @Transactional
    public Long createChatMessage(ChatMessage chatMessage) {
        ChatRoomJpaEntity chatRoomJpaEntity = chatRoomPostRepository.findById(chatMessage.getChatRoomId().value())
                .orElseThrow(RuntimeException::new);
        ChatMessageJpaEntity chatMessageJpaEntity = ChatMessageJpaEntity.builder()
                .chatRoom(chatRoomJpaEntity)
                .content(chatMessage.getContent())
                .writer(chatMessage.getWriter())
                .build();
        chatRoomJpaEntity.createMessage(chatMessageJpaEntity);
        chatRoomPostRepository.save(chatRoomJpaEntity);
        return chatMessageJpaEntity.getChatMessageId();
    }
}
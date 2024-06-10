package com.example.dormitorybe.adapter;

import com.example.dormitorybe.common.annotation.PersistenceAdapter;
import com.example.dormitorybe.domain.ChatMessage;
import com.example.dormitorybe.domain.ChatRoom;
import com.example.dormitorybe.entity.ChatMessageJpaEntity;
import com.example.dormitorybe.entity.ChatRoomJpaEntity;
import com.example.dormitorybe.port.out.LoadChatMessagePort;
import com.example.dormitorybe.repository.ChatMessageRepository;
import com.example.dormitorybe.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
class ChatMessageLoadPersistenceAdapter implements LoadChatMessagePort {
    private final ChatRoomRepository springDataChatRoomRepository;
    private final ChatMessageRepository springDataChatMessageRepository;

    @Transactional
    @Override
    public List<ChatMessage> loadChatMessegeList(Long roomId, PageRequest pageRequest) {
        ChatRoomJpaEntity chatRoomJpaEntity = springDataChatRoomRepository.findById(roomId)
                .orElseThrow(RuntimeException::new);
        Slice<ChatMessageJpaEntity> chatMessageJpaEntitySlice = springDataChatMessageRepository.findAllByChatRoom(chatRoomJpaEntity, pageRequest);
        return chatMessageJpaEntitySlice.getContent().stream().map(
                (chatMessageJpaEntity)-> ChatMessage.builder()
                        .chatId(new ChatMessage.ChatId(chatMessageJpaEntity.getChatMessageId()))
                        .content(chatMessageJpaEntity.getContent())
                        .writer(chatMessageJpaEntity.getWriter())
                        .chatRoomId(new ChatRoom.RoomId(chatRoomJpaEntity.getChatRoomId()))
                        .build()
        ).collect(Collectors.toList());
    }
}
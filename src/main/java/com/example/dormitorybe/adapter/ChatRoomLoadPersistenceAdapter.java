package com.example.dormitorybe.adapter;

import com.example.dormitorybe.common.annotation.PersistenceAdapter;
import com.example.dormitorybe.domain.ChatRoom;
import com.example.dormitorybe.entity.ChatRoomJpaEntity;
import com.example.dormitorybe.port.out.LoadChatRoomPort;
import com.example.dormitorybe.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
class ChatRoomLoadPersistenceAdapter implements LoadChatRoomPort {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public ChatRoom loadById(Long roomId, PageRequest pageRequest) {
        ChatRoomJpaEntity chatRoomJpaEntity = chatRoomRepository.findById(roomId)
                .orElseThrow(RuntimeException::new);
        return ChatRoom.builder()
                .roomId(new ChatRoom.RoomId(chatRoomJpaEntity.getChatRoomId()))
                .build();
    }

    @Override
    public List<ChatRoom> search(PageRequest pageRequest) {
        Slice<ChatRoomJpaEntity> chatRoomJpaEntityList = chatRoomRepository.findAllBy(pageRequest);
        return chatRoomJpaEntityList.stream()
                .map(chatRoomJpaEntity -> ChatRoom.builder()
                        .roomId(new ChatRoom.RoomId(chatRoomJpaEntity.getChatRoomId()))
                        .build())
                .collect(Collectors.toList());
    }
}
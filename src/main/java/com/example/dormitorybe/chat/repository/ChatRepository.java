package com.example.dormitorybe.chat.repository;

import com.example.dormitorybe.chat.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ChatRepository {
    private final Map<String, ChatRoomDto> chatRooms;

    public void save(String roomId, ChatRoomDto chatRoom) {
        chatRooms.put(roomId, chatRoom);
    }

    public ChatRoomDto findById(String roomId) {
        return chatRooms.get(roomId);
    }

    public List<ChatRoomDto> findAll() {
        return new ArrayList<>(chatRooms.values());
    }
}

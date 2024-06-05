package com.example.dormitorybe.chat.service;

import com.example.dormitorybe.chat.dto.ChatMessageDto;
import com.example.dormitorybe.chat.dto.ChatRoomDto;
import com.example.dormitorybe.chat.repository.ChatRepository;
import com.example.dormitorybe.config.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public List<ChatRoomDto> findAll() {
        return chatRepository.findAll();
    }

    public ChatRoomDto findRoomById(String roomId) {
        return chatRepository.findById(roomId);
    }

    public ChatRoomDto createRoom(String name) {
        String roomId = UUID.randomUUID().toString();
        ChatRoomDto chatRoom = ChatRoomDto.of(roomId, name);
        chatRepository.save(roomId, chatRoom);
        return chatRoom;
    }

    public void handleAction(
            String roomId,
            WebSocketSession session,
            ChatMessageDto chatMessage
    ) {
        ChatRoomDto room = findRoomById(roomId);

        if (isEnterRoom(chatMessage)) {
            room.join(session);
            chatMessage.setMessage(chatMessage.getSender() + "님 환영합니다.");
        }

        TextMessage textMessage = Util.Chat.resolveTextMessage(chatMessage);
        room.sendMessage(textMessage);
    }

    private boolean isEnterRoom(ChatMessageDto chatMessage) {
        return chatMessage.getMessageType().equals(ChatMessageDto.MessageType.ENTER);
    }
}

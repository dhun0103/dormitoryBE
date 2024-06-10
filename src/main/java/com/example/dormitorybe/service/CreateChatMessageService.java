package com.example.dormitorybe.service;

import com.example.dormitorybe.common.annotation.UseCase;
import com.example.dormitorybe.domain.ChatMessage;
import com.example.dormitorybe.domain.ChatRoom;
import com.example.dormitorybe.port.in.ChatMessageCreateUseCase;
import com.example.dormitorybe.port.in.command.ChatMessageCreateCommand;
import com.example.dormitorybe.port.out.CreateChatMessagePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
class CreateChatMessageService implements ChatMessageCreateUseCase {
    private final CreateChatMessagePort createChatMessagePort;

    @Override
    public Long createChatMessage(ChatMessageCreateCommand command) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoomId(new ChatRoom.RoomId(command.roomId()))
                .content(command.content())
                .writer(command.from())
                .build();
        return createChatMessagePort.createChatMessage(chatMessage);
    }
}
package com.example.dormitorybe.service;

import com.example.dormitorybe.common.annotation.UseCase;
import com.example.dormitorybe.domain.ChatRoom;
import com.example.dormitorybe.port.in.ChatRoomCreateUseCase;
import com.example.dormitorybe.port.in.command.ChatRoomCreateCommand;
import com.example.dormitorybe.port.out.CreateChatRoomPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
class CreateChatRoomService implements ChatRoomCreateUseCase {
    private final CreateChatRoomPort createChatRoomPort;
    @Override
    public boolean createChatRoom(ChatRoomCreateCommand command) {
        ChatRoom chatRoom = ChatRoom.builder()
                .build();
        return createChatRoomPort.createChatRoom(chatRoom);
    }

}
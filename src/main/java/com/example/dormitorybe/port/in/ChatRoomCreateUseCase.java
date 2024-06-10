package com.example.dormitorybe.port.in;

import com.example.dormitorybe.port.in.command.ChatRoomCreateCommand;

public interface ChatRoomCreateUseCase {
    boolean createChatRoom(ChatRoomCreateCommand command);
}
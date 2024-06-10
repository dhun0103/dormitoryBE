package com.example.dormitorybe.port.out;

import com.example.dormitorybe.domain.ChatRoom;

public interface CreateChatRoomPort {
    boolean createChatRoom(ChatRoom chatRoom);
}
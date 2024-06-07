package com.example.dormitorybe.port.out;

import com.example.dormitorybe.domain.ChatMessage;

public interface CreateChatMessagePort {
    Long createChatMessage(ChatMessage chatMessage);
}

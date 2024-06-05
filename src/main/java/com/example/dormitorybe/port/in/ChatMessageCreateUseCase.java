package com.example.dormitorybe.port.in;

import com.example.dormitorybe.port.in.command.ChatMessageCreateCommand;

public interface ChatMessageCreateUseCase {
    Long createChatMessage(ChatMessageCreateCommand command);
}

package com.example.dormitorybe.port.in;

import com.example.dormitorybe.dto.ResDto.ChatMessageResDto;
import com.example.dormitorybe.port.in.query.ChatMessageListQuery;

import java.util.List;

public interface ChatMessageLoadUseCase {
    List<ChatMessageResDto> getChatMessageList(ChatMessageListQuery command);
}

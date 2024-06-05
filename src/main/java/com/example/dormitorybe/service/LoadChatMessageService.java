package com.example.dormitorybe.service;

import com.example.dormitorybe.common.annotation.UseCase;
import com.example.dormitorybe.dto.ResDto.ChatMessageResDto;
import com.example.dormitorybe.port.in.ChatMessageLoadUseCase;
import com.example.dormitorybe.port.in.query.ChatMessageListQuery;
import com.example.dormitorybe.port.out.LoadChatMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
class LoadChatMessageService implements ChatMessageLoadUseCase {
    private final LoadChatMessagePort loadChatMessagePort;
    @Override
    public List<ChatMessageResDto> getChatMessageList(ChatMessageListQuery query) {
        PageRequest pageRequest = PageRequest.of(query.page(), query.size(), Sort.by("chatMessageId").descending());
        return loadChatMessagePort.loadChatMessegeList(query.roomId(), pageRequest)
                .stream().map((chatMessage)->
                        ChatMessageResDto.builder()
                                .id(chatMessage.getChatId().value())
                                .content(chatMessage.getContent())
                                .writer(chatMessage.getWriter())
                                .build())
                .collect(Collectors.toList());
    }
}
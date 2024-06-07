package com.example.dormitorybe.controller;

import com.example.dormitorybe.common.response.SuccessApiResponse;
import com.example.dormitorybe.port.in.ChatMessageLoadUseCase;
import com.example.dormitorybe.port.in.query.ChatMessageListQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
class ChatMessageController {
    private final ChatMessageLoadUseCase chatMessageLoadUseCase;

    @GetMapping("/{roomId}/messages")
    public SuccessApiResponse<?> getMessageList(@PathVariable Long roomId,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size
    ){
        ChatMessageListQuery query = ChatMessageListQuery.builder()
                .roomId(roomId)
                .page(page)
                .size(size)
                .build();
        return SuccessApiResponse.of(chatMessageLoadUseCase.getChatMessageList(query));
    }
}
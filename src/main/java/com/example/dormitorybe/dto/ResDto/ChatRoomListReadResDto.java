package com.example.dormitorybe.dto.ResDto;

import lombok.Builder;

import java.util.List;

@Builder
public record ChatRoomListReadResDto(List<ChatRoomItemResDto> messageList, boolean hasNext) {
}
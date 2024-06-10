package com.example.dormitorybe.port.in.query;

import lombok.Builder;

@Builder
public record ChatMessageListQuery(Long roomId, int page, int size) {
}
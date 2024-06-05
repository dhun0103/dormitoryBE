package com.example.dormitorybe.dto.ResDto;

import lombok.Builder;

@Builder
public record ChatMessageResDto(Long id, String content, String writer) {
}
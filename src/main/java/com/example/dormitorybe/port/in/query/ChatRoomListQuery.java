package com.example.dormitorybe.port.in.query;

import lombok.Builder;

@Builder
public record ChatRoomListQuery(int page, int size) {
}

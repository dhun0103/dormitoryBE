package com.example.dormitorybe.port.in;

import com.example.dormitorybe.dto.ResDto.ChatRoomListReadResDto;
import com.example.dormitorybe.dto.ResDto.ChatRoomResDto;
import com.example.dormitorybe.port.in.query.ChatRoomListQuery;
import com.example.dormitorybe.port.in.query.ChatRoomQuery;

public interface ChatRoomLoadUseCase {
    ChatRoomResDto getChatRoomById(ChatRoomQuery chatRoomQuery);
    ChatRoomListReadResDto getChatRoomList(ChatRoomListQuery query);
}
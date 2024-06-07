package com.example.dormitorybe.service;

import com.example.dormitorybe.common.annotation.UseCase;
import com.example.dormitorybe.domain.ChatRoom;
import com.example.dormitorybe.dto.ResDto.ChatRoomListReadResDto;
import com.example.dormitorybe.dto.ResDto.ChatRoomResDto;
import com.example.dormitorybe.dto.ResDto.ChatRoomItemResDto;
import com.example.dormitorybe.port.in.ChatRoomLoadUseCase;
import com.example.dormitorybe.port.in.query.ChatRoomListQuery;
import com.example.dormitorybe.port.in.query.ChatRoomQuery;
import com.example.dormitorybe.port.out.LoadChatRoomPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
class LoadChatRoomService implements ChatRoomLoadUseCase {
    private final LoadChatRoomPort loadChatRoomPort;
    @Override
    public ChatRoomResDto getChatRoomById(ChatRoomQuery chatRoomQuery) {
        PageRequest pageRequest = PageRequest.of(chatRoomQuery.page(), chatRoomQuery.size());
        ChatRoom chatRoom = loadChatRoomPort.loadById(chatRoomQuery.id(), pageRequest);

        return ChatRoomResDto.builder()
                .roomId(chatRoom.getRoomId().value())
//                .messageList(chatRoom.getMessageList()
//                    .stream().map((chatMessage ->
//                        ChatMessageResponse.builder()
//                            .id(chatMessage.getChatId().value())
//                            .content(chatMessage.getContent())
//                            .writer(chatMessage.getWriter())
//                            .build()
//                    )).collect(Collectors.toList()))
                .build();
    }

    @Override
    public ChatRoomListReadResDto getChatRoomList(ChatRoomListQuery query) {
        PageRequest pageRequest = PageRequest.of(query.page(), query.size(), Sort.by("chatRoomId").descending());
        List<ChatRoom> chatRoomList = loadChatRoomPort.search(pageRequest);
        ChatRoomListReadResDto response = ChatRoomListReadResDto.builder()
                .messageList(chatRoomList.stream().map(chatRoom -> ChatRoomItemResDto.builder()
                                .roomId(chatRoom.getRoomId().value())
                                .build())
                        .collect(Collectors.toList()))
                .build();
        return response;
    }
}
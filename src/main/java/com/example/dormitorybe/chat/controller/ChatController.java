package com.example.dormitorybe.chat.controller;

import com.example.dormitorybe.chat.dto.ChatRoomDto;
import com.example.dormitorybe.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoomDto createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoomDto> getAll() {
        return chatService.findAll();
    }
}

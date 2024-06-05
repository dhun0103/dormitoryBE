package com.example.dormitorybe.chat.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageDto {
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;
}

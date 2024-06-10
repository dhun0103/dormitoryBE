package com.example.dormitorybe.port.out;

import java.util.List;
import com.example.dormitorybe.domain.ChatMessage;
import org.springframework.data.domain.PageRequest;

public interface LoadChatMessagePort {
    List<ChatMessage> loadChatMessegeList(Long roomId, PageRequest pageRequest);
}
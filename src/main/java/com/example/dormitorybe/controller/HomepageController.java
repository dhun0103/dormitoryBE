package com.example.dormitorybe.controller;

import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.service.NoticePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/homepage")
public class HomepageController {

    private final NoticePostService noticePostService;

    @GetMapping
    public GlobalResDto<?> getLatestThreeNoticePosts() {
        return noticePostService.getLatestThreeNoticePosts();
    }



}

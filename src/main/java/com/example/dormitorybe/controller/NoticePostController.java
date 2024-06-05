package com.example.dormitorybe.controller;

import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.service.NoticePostService;
import lombok.RequiredArgsConstructor;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoticePostController {


    private final NoticePostService noticePostService;

    @GetMapping("/notice/kulhouse_notice")
    public GlobalResDto getKulhouse_notice(){
        return noticePostService.crawlNoticePost("https://kulhouse.konkuk.ac.kr/home/sub04/sub04_01.asp" +
                "?intNowPage=1&board_nm=kulhouse_notice&search_m=&search_t=&intNoticeCnt=27");
    }
    @GetMapping("/notice/serveone_notice")
    public GlobalResDto getServeone_notice(){
        return noticePostService.crawlNoticePost("https://kulhouse.konkuk.ac.kr/home/sub04/sub05_07.asp" +
                "?intNowPage=1&board_nm=serveone_notice&search_m=&search_t=&intNoticeCnt=2");
    }
    @GetMapping("/notice/ourhome_notice")
    public GlobalResDto getOurhome_notice(){
        return noticePostService.crawlNoticePost("https://kulhouse.konkuk.ac.kr/home/sub04/sub05_05.asp" +
                "?intNowPage=1&board_nm=ourhome_notice&search_m=&search_t=&intNoticeCnt=16");
    }

}

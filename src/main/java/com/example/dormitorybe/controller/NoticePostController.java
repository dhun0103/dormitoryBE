package com.example.dormitorybe.controller;

import com.example.dormitorybe.dto.ReqDto.NoticePostReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.service.NoticePostService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



@RestController
public class NoticePostController {


    private final NoticePostService noticePostService;

    public NoticePostController(NoticePostService noticePostService) {
        this.noticePostService = noticePostService;
    }

    @GetMapping("/notices")
    public GlobalResDto getNotice(){
        return noticePostService.crawlNoticePost();
    }
/*    @GetMapping("/notices/school")
    public GlobalResDto<?> getSchool() {
        return noticePostService.crawlNoticePost("https://www.konkuk.ac.kr/konkuk/2238/subview.do", 0);

    }
    @GetMapping("/notices/scholarship")
    public GlobalResDto<?> getScholarships(){

        return noticePostService.crawlNoticePost("https://www.konkuk.ac.kr/konkuk/2239/subview.do", 1);

    }
//    @GetMapping("/notices/job")
//    public GlobalResDto<?> getJobs(){
//
//        return crawlNoticePost("https://www.konkuk.ac.kr/konkuk/2240/subview.do", 2);
//
//    }
    @GetMapping("/notices/international")
    public GlobalResDto<?> getInternationals(){

        return noticePostService.crawlNoticePost("https://www.konkuk.ac.kr/konkuk/2241/subview.do", 3);

    }
    @GetMapping("/notices/student")
    public GlobalResDto<?> getStudents(){

        return noticePostService.crawlNoticePost("https://www.konkuk.ac.kr/konkuk/2242/subview.do", 4);

    }*/



}
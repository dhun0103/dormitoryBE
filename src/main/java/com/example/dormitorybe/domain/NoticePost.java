package com.example.dormitorybe.domain;

import com.example.dormitorybe.dto.ReqDto.NoticePostReqDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class NoticePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticePostId;

    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String date;
    @Column(nullable = false)
    String url;


    public NoticePost(NoticePostReqDto noticePostReqDto) {
        this.title = noticePostReqDto.getTitle();
        this.date = noticePostReqDto.getDate();
        this.url = noticePostReqDto.getUrl();


    }
}

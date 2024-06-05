package com.example.dormitorybe.domain;

import com.example.dormitorybe.dto.ReqDto.NoticePostReqDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class NoticePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticePostId;

    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String writer;
    @Column(nullable = false)
    String date;
    @Column(nullable = false)
    String visits;
    @Column(nullable = false)
    String url;


    public NoticePost(NoticePostReqDto noticePostReqDto) {
        this.title = noticePostReqDto.getTitle();
        this.writer = noticePostReqDto.getWriter();
        this.date = noticePostReqDto.getDate();
        this.visits = noticePostReqDto.getVisits();
        this.url = noticePostReqDto.getUrl();


    }

}

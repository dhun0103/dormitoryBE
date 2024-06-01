package com.example.dormitorybe.dto.ReqDto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticePostReqDto {

    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String date;
    @Column(nullable = false)
    String url;
    @Column(nullable = false)
    int noticeType;

}

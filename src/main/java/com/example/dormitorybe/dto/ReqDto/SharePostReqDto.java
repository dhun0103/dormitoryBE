package com.example.dormitorybe.dto.ReqDto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SharePostReqDto {

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String contents;

}

package com.example.dormitorybe.dto.ReqDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDto {

    @Column(nullable = false)
    private String studentNum;
    @Column(nullable = false)
    private String pw;
}

package com.example.dormitorybe.dto.ReqDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpReqDto {

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String pw;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int grade;
    @Column(nullable = false)
    private String hall;
    @Column(nullable = false)
    private String roomNum;

    public void setEncodePwd(String encodePwd) {

        this.pw = encodePwd;
    }
}

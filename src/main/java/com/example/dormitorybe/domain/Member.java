package com.example.dormitorybe.domain;

import com.example.dormitorybe.dto.ReqDto.SignUpReqDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String studentNum;
    @Column(nullable = false)
    private String pw;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int grade;
    @Column(nullable = false)
    private String hall;
    @Column(nullable = false)
    private int roomNum;

    public Member(SignUpReqDto signUpReqDto) {
        this.studentNum = signUpReqDto.getStudentNum();
        this.pw = signUpReqDto.getPw();
        this.name = signUpReqDto.getName();
        this.grade = signUpReqDto.getGrade();
        this.hall = signUpReqDto.getHall();
        this.roomNum = signUpReqDto.getRoomNum();
    }
}

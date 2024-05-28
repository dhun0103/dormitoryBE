package com.example.dormitorybe.domain;

import com.example.dormitorybe.dto.ReqDto.MemberReqDto;
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

    public Member(MemberReqDto memberReqDto) {
        this.studentNum = memberReqDto.getStudentNum();
        this.pw = memberReqDto.getPw();
        this.name = memberReqDto.getName();
        this.grade = memberReqDto.getGrade();
        this.hall = memberReqDto.getHall();
        this.roomNum = memberReqDto.getRoomNum();
    }
}

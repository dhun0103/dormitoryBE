package com.example.dormitorybe.dto.ResDto;

import com.example.dormitorybe.domain.Member;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResDto {

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

    public ProfileResDto(Member member) {
        this.studentNum = member.getStudentNum();
        this.pw = member.getPw();
        this.name = member.getName();
        this.grade = member.getGrade();
        this.hall = member.getHall();
        this.roomNum = member.getRoomNum();
    }
}

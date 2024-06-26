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

    private String email;
    private String name;
    private int grade;
    private String hall;
    private String roomNum;

    public ProfileResDto(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.grade = member.getGrade();
        this.hall = member.getHall();
        this.roomNum = member.getRoomNum();
    }
}

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
    private String email;
    @Column(nullable = false)
    private String pw;

    public Member(MemberReqDto memberReqDto) {
        this.email = memberReqDto.getEmail();
        this.pw = memberReqDto.getPw();
    }
}

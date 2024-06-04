package com.example.dormitorybe.dto.ReqDto;

import com.example.dormitorybe.domain.Member;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileReqDto {

    @Column(nullable = false)
    private String email;
}

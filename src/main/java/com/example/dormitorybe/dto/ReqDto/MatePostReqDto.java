package com.example.dormitorybe.dto.ReqDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MatePostReqDto {

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String contents;

    @Column(nullable = false)
    String gender;

    @Column(nullable = false)
    int age;

    @Column(nullable = false)
    String mbti;

    @Column(nullable = false)
    int wakeUpStart;

    @Column(nullable = false)
    int wakeUpEnd;

    @Column(nullable = false)
    int sleepStart;

    @Column(nullable = false)
    int sleepEnd;

    @Column(nullable = false)
    int showerStart;

    @Column(nullable = false)
    int showerEnd;

    @Column(nullable = false)
    String dayOfWeek;

    @Column(nullable = false)
    String snoring;

    @Column(nullable = false)
    int noiseSense;

    @Column(nullable = false)
    int light;

    @Column(nullable = false)
    int noisiness;

    @Column(nullable = false)
    Boolean smoking;

    @Column(nullable = false)
    int personalTime;

    @Column(nullable = false)
    int familiarity;

    @Column(nullable = false)
    int deliveryFood;

    @Column(nullable = false)
    int homeProtector;

    @Column(nullable = false)
    int gameAndCall;
}

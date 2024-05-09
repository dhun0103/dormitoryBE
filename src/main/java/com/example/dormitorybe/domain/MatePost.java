package com.example.dormitorybe.domain;

import com.example.dormitorybe.dto.ReqDto.MatePostReqDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
public class MatePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matePostId;
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

    public MatePost(MatePostReqDto matePostReqDto){
        this.title=matePostReqDto.getTitle();
        this.contents = matePostReqDto.getContents();
        this.gender = matePostReqDto.getGender();
        this.age = matePostReqDto.getAge();
        this.mbti = matePostReqDto.getMbti();
        this.wakeUpStart = matePostReqDto.getWakeUpStart();
        this.wakeUpEnd = matePostReqDto.getWakeUpEnd();
        this.sleepStart = matePostReqDto.getSleepStart();
        this.sleepEnd = matePostReqDto.getSleepEnd();
        this.showerStart = matePostReqDto.getShowerStart();
        this.showerEnd = matePostReqDto.getShowerEnd();
        this.dayOfWeek = matePostReqDto.getDayOfWeek();
        this.snoring = matePostReqDto.getSnoring();
        this.noiseSense = matePostReqDto.getNoiseSense();
        this.light = matePostReqDto.getLight();
        this.noisiness = matePostReqDto.getNoisiness();
        this.smoking = matePostReqDto.getSmoking();
        this.personalTime = matePostReqDto.getPersonalTime();
        this.familiarity = matePostReqDto.getFamiliarity();
        this.deliveryFood = matePostReqDto.getDeliveryFood();
        this.homeProtector = matePostReqDto.getHomeProtector();
        this.gameAndCall = matePostReqDto.getGameAndCall();
    }
}

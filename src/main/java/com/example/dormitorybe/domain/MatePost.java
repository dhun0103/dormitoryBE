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
    String smoking;
    @Column(nullable = false)
    int deliveryFood;
    @Column(nullable = false)
    int gameAndCall;
    @Column(nullable = false)
    int homeProtector;
    @Column(nullable = false)
    int cleaning;
    @Column(nullable = false)
    int killBug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public MatePost(MatePostReqDto matePostReqDto, Member member){
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
        this.smoking = matePostReqDto.getSmoking();
        this.deliveryFood = matePostReqDto.getDeliveryFood();
        this.gameAndCall = matePostReqDto.getGameAndCall();
        this.homeProtector = matePostReqDto.getHomeProtector();
        this.cleaning = matePostReqDto.getCleaning();
        this.killBug = matePostReqDto.getKillBug();
        this.member = member;
    }

    public void updateMatePost(MatePostReqDto matePostReqDto){
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
        this.smoking = matePostReqDto.getSmoking();
        this.deliveryFood = matePostReqDto.getDeliveryFood();
        this.gameAndCall = matePostReqDto.getGameAndCall();
        this.homeProtector = matePostReqDto.getHomeProtector();
        this.cleaning = matePostReqDto.getCleaning();
        this.killBug = matePostReqDto.getKillBug();
    }
}

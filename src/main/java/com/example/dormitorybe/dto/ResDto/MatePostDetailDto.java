package com.example.dormitorybe.dto.ResDto;

import com.example.dormitorybe.domain.MatePost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MatePostDetailDto {

    private Long matePostId;
    private String title;
    private String contents;
    private String gender;
    private int age;
    String mbti;
    int wakeUpStart;
    int wakeUpEnd;
    int sleepStart;
    int sleepEnd;
    int showerStart;
    int showerEnd;
    String dayOfWeek;
    String smoking;
    int deliveryFood;
    int gameAndCall;
    int homeProtector;
    int cleaning;
    int killBug;

    public MatePostDetailDto(MatePost matePost) {
        this.matePostId = matePost.getMatePostId();
        this.title = matePost.getTitle();
        this.contents = matePost.getContents();
        this.gender = matePost.getGender();
        this.age = matePost.getAge();
        this.mbti = matePost.getMbti();
        this.wakeUpEnd = matePost.getWakeUpEnd();
        this.sleepStart = matePost.getSleepStart();
        this.sleepEnd = matePost.getSleepEnd();
        this.showerStart = matePost.getShowerStart();
        this.showerEnd = matePost.getShowerEnd();
        this.dayOfWeek = matePost.getDayOfWeek();
        this.smoking = matePost.getSmoking();
        this.deliveryFood = matePost.getDeliveryFood();
        this.gameAndCall = matePost.getGameAndCall();
        this.homeProtector = matePost.getHomeProtector();
        this.cleaning = matePost.getCleaning();
        this.killBug = matePost.getKillBug();
    }
}

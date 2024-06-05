package com.example.dormitorybe.domain;

import com.example.dormitorybe.dto.ReqDto.MealReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;
    @Column(nullable = false)
    String date;
    @Column(nullable = false)
    String menu;
    @Column(nullable = false)
    char mealType;
    @Column(nullable = false)
    String mealTime;

    public Meal(MealReqDto mealReqDto) {
        this.date = mealReqDto.getDate();
        this.menu = mealReqDto.getMenu();
        this.mealType = mealReqDto.getMealType();
        this.mealTime = mealReqDto.getMealTime();
    }

}

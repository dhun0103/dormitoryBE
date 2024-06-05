package com.example.dormitorybe.dto.ReqDto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealReqDto {

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String menu;

    @Column(nullable = false)
    char mealType;

    @Column(nullable = false)
    String mealTime;

}
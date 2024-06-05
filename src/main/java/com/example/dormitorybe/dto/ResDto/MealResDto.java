package com.example.dormitorybe.dto.ResDto;

import com.example.dormitorybe.domain.MatePost;
import com.example.dormitorybe.domain.Meal;
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
public class MealResDto {
    private Long mealId;
    String date;
    String menu;
    char mealType;
    String mealTime;

    public MealResDto(Meal meal) {
        this.mealId = meal.getMealId();
        this.date = meal.getDate();
        this.menu = meal.getMenu();
        this.mealType = meal.getMealType();
        this.mealTime = meal.getMealTime();
    }
}

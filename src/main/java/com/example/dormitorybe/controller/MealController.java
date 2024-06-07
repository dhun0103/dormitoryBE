package com.example.dormitorybe.controller;


import com.example.dormitorybe.dto.ReqDto.MealReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/meal")
    public GlobalResDto<?> getMeals(){

        return mealService.getAllMeals();
    }

    @PostMapping("/meals")
    public GlobalResDto<?> createMeals(@RequestBody List<MealReqDto> mealReqDtoList) {
        return mealService.createMeals(mealReqDtoList);
    }


}

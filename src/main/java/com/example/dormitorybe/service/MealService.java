package com.example.dormitorybe.service;

import com.example.dormitorybe.domain.MatePost;
import com.example.dormitorybe.domain.Meal;
import com.example.dormitorybe.dto.ReqDto.MealReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.dto.ResDto.MatePostResDto;
import com.example.dormitorybe.dto.ResDto.MealResDto;
import com.example.dormitorybe.repository.MealRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MealService {

    private final MealRepository mealRepository;

    @Transactional
    public GlobalResDto<?> getAllMeals() {
        List<Meal> mealList = mealRepository.findAll();
        List<MealResDto> mealResDtoList = mealList.stream()
                .map(MealResDto::new)
                .collect(Collectors.toList());

        return GlobalResDto.success(mealResDtoList, "success get Meals");
    }

    @Transactional
    public GlobalResDto<?> createMeals(List<MealReqDto> mealReqDtoList) {
        List<Meal> meals = mealReqDtoList.stream()
                .map(Meal::new)
                .collect(Collectors.toList());
        mealRepository.saveAll(meals);

        return GlobalResDto.success(null, "success create multiple Meals");
    }
}

package com.example.dormitorybe.repository;


import com.example.dormitorybe.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}


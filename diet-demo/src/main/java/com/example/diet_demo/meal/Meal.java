package com.example.diet_demo.meal;

public record Meal(
        Long id,
        java.time.LocalDateTime date,
        MealType mealType,
        int calories,
        int protein,
        int carbs,
        int fat
) { }


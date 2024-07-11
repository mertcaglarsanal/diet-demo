package com.example.diet_demo.meal;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class MealRepository {


    List<Meal> meals = new ArrayList<>();



    public List<Meal> findAll() {
        return meals;
    }

    public Optional<Meal> findById(Long id) {
        return meals.stream()
                .filter(meal -> meal.id().equals(id))
                .findFirst();
    }

    public void create(Meal meal) {
        System.out.println(meal);

            meals.add(meal);




    }

    public void update(Meal updatedMeal) {
        meals.stream()
                .filter(meal -> meal.id().equals(updatedMeal.id()))
                .findFirst()
                .ifPresent(meal -> {
                    // Since records are immutable, you'll need to replace the meal in the list
                    meals.remove(meal);
                    meals.add(updatedMeal);
                });
    }

    public void delete(Long id) {
        meals.removeIf(meal -> meal.id().equals(id));
    }

    @PostConstruct
    private void init() {
        meals.add(new Meal(1L, LocalDateTime.now(), MealType.BREAKFAST, 300, 20, 50, 10));
        meals.add(new Meal(2L, LocalDateTime.now().plusDays(1), MealType.LUNCH, 600, 30, 70, 20));
    }
}

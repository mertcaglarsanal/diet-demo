package com.example.diet_demo.meal;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class MealRepository {


    List<Meal> meals = new ArrayList<>();
    private final JdbcClient jdbcClient;


    public MealRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    public List<Meal> findAll(){
        return jdbcClient.sql("select * from meal").
                query(Meal.class)
                .list();
    }
    public Optional<Meal> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM meal WHERE id = ?")
                .param(id)
                .query(Meal.class)
                .optional();
    }

    public void create(Meal meal) {
        jdbcClient.sql("INSERT INTO meal (date, meal_type, calories, protein, carbs, fat) VALUES (?, ?, ?, ?, ?, ?)")
                .param(meal.date())
                .param(meal.mealType().name())
                .param(meal.calories())
                .param(meal.protein())
                .param(meal.carbs())
                .param(meal.fat())
                .update();
    }

    public void update(Meal meal) {
        jdbcClient.sql("UPDATE meal SET date = ?, meal_type = ?, calories = ?, protein = ?, carbs = ?, fat = ? WHERE id = ?")
                .param(meal.date())
                .param(meal.mealType().name())
                .param(meal.calories())
                .param(meal.protein())
                .param(meal.carbs())
                .param(meal.fat())
                .param(meal.id())
                .update();
    }

    public void delete(Long id) {
        jdbcClient.sql("DELETE FROM meal WHERE id = ?")
                .param(id)
                .update();
    }
    public int count() {
        return jdbcClient.sql("SELECT COUNT(*) FROM meal")
                .query(Integer.class)
                .single();
    }
    public void saveAll(List<Meal>meals){
        meals.stream().forEach(this::create);
    }


}

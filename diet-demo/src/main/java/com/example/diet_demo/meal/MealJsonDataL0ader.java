package com.example.diet_demo.meal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class MealJsonDataL0ader implements CommandLineRunner
{
    private final MealRepository mealRepository;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(MealJsonDataL0ader.class);
            public MealJsonDataL0ader(MealRepository mealRepository,ObjectMapper objectMapper){
             this.mealRepository = mealRepository;
             this.objectMapper = objectMapper;
             }
    @Override
    public void run(String... args) throws Exception {
                if (mealRepository.count()==0){
                    try(InputStream inputStream = TypeReference.class.getResourceAsStream("data/meals.json")){
//                        if (inputStream == null) {
//                            throw new RuntimeException("ben levo");
//                        }
                        Meals allMeals = objectMapper.readValue(inputStream, Meals.class);
                        log.info("Reading runs from json data and saving it",allMeals.meals().size());
                        mealRepository.saveAll(allMeals.meals());
                    }
            catch (IOException e){
        throw new RuntimeException("failed to read JSON data"+e);
            }
                }

    }
}

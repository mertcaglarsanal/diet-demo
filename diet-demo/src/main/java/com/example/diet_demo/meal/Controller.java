package com.example.diet_demo.meal;

import com.example.diet_demo.DietDemoApplication;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/api/meals")


public class Controller {
    private final Logger log = LoggerFactory.getLogger(DietDemoApplication.class);
    //Logger logger = Logger.getLogger(DietDemoApplication.class.getName());
    private final MealRepository mealRepository;

        public Controller(MealRepository mealRepository) {
            this.mealRepository = mealRepository;
        }

   @GetMapping
    public List<Meal> getAllMeals() {
      log.info("sdsds");
        return mealRepository.findAll();
   }

    @GetMapping("/{id}")
   public Meal getMealById(@PathVariable Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meal not found with id: " + id));
    }

    @PostMapping()
    public void createMeal(@RequestBody Meal meal) {
        mealRepository.create(meal);
   }

    @PutMapping("/{id}")
   public void updateMeal(@PathVariable Long id, @RequestBody Meal updatedMeal) {
        // Ensure the id in updatedMeal matches the path variable id
        if (!updatedMeal.id().equals(id)) {
            throw new RuntimeException("Id mismatch");
       }
       mealRepository.update(updatedMeal);
   }

  @DeleteMapping("/{id}")
    public void deleteMeal(@PathVariable Long id) {
        mealRepository.delete(id);
  }
}
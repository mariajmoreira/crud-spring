package com.crud.crudspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crudspring.model.Food;
import com.crud.crudspring.model.InsaFood;
import com.crud.crudspring.repository.FoodRepository;
import com.crud.crudspring.service.FoodService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/foods")
public class FoodController {

    private FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }


     @GetMapping("/insa")
    public List<InsaFood> getInsaFoodList(){
        System.out.println("getting insa foods");
        List<InsaFood> insa_tca = foodService.insaFoodList();


        return insa_tca;
    }

     @GetMapping
        public List<Food> getFoodList(){
        System.out.println("getting insa foods");
        List<Food> foods = foodService.foodList();


        return foods;
    }


     @GetMapping("/transfer")
    public List<Food> transferInsaFoodList(){
        System.out.println("getting insa foods");
        List<InsaFood> insa_tca = foodService.insaFoodList();
        System.out.println("insa_tca: " + insa_tca);
        for(InsaFood insa : insa_tca){
            Food f = new Food(insa) ;
            System.out.println("food: " + f);
            foodService.create(f);
        }

        List<Food> food = foodService.foodList();

        return food;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Food> findById(@PathVariable  @NotNull @Positive Long id){
        return foodService.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll(){
        this.foodService.deleteAll();
        return  ResponseEntity.noContent().<Void>build();
       
    }

    @DeleteMapping("/deleteAllById")
    public ResponseEntity<Void> deleteAllById(){
        List<Food> food = foodService.foodList();
        for(Food f : food){
        
            Long id = f.getId();
            if ( this.foodService.delete(id)) {
                return ResponseEntity.noContent().<Void>build();
            }
           
             return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
       
    }
/* 
    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public Food create(@RequestBody @Valid Food food){
       return foodService.create(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> update(@PathVariable @NotNull @Positive String id, @RequestBody @Valid Food food){
        return foodService.update(id,food)
        .map(recordFound -> {
            return ResponseEntity.ok().body(recordFound);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable  @NotNull @Positive Long id){
       if(foodService.delete(id)){
        return ResponseEntity.noContent().<Void>build();
       }
      return ResponseEntity.notFound().build();
    
    } */
}

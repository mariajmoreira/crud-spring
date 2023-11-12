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
import com.crud.crudspring.repository.FoodRepository;
import com.crud.crudspring.service.FoodService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/foods")
public class FoodController {

    private FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public List<Food> list(){
        return foodService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> findById(@PathVariable  @NotNull @Positive String id){
        return foodService.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
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

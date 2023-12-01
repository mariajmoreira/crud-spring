package com.crud.crudspring.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.crud.crudspring.model.Food;
import com.crud.crudspring.model.InsaFood;
import com.crud.crudspring.repository.FoodRepository;
import com.crud.crudspring.repository.InsaFoodRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class FoodService {
    
    private final FoodRepository foodRepository;

    private final InsaFoodRepository insafoodRepository;

    public FoodService(FoodRepository foodRepository, InsaFoodRepository insaFoodRepository){
        this.foodRepository = foodRepository;
        this.insafoodRepository=insaFoodRepository;

    }

    public List<InsaFood> insaFoodList(){
        return insafoodRepository.findAll();
    }

    public List<Food> foodList(){
        return foodRepository.findAll();
    }

    public Optional<Food> findById(@PathVariable  @NotNull @Positive Long id){
        return foodRepository.findById(id);
       // .map(recordFound -> ResponseEntity.ok().body(recordFound))
        //.orElse(ResponseEntity.notFound().build());
    }

   
    public Food create(@RequestBody @Valid Food alimento){
       return foodRepository.save(alimento);
    }

     public void deleteAll(){
       foodRepository.deleteAll();
       
     } 

       public boolean delete(@PathVariable  @NotNull @Positive Long id){
        return foodRepository.findById(id)
        .map(recordFound -> {
             foodRepository.deleteById(id);
             return true;
        })
        .orElse(false);
     } 


/*     public Optional<Food> update(@PathVariable @NotNull @Positive String id, @RequestBody @Valid Food alimento){
        return foodRepository.findById(id)
        .map(recordFound -> {
            recordFound.setName(alimento.getName());
            recordFound.setCategory(alimento.getCategory());
            recordFound.setCalories(alimento.getCalories());
            recordFound.setCarbs(alimento.getCarbs());
            recordFound.setFats(alimento.getFats());
            recordFound.setProtein(alimento.getProtein());
            return foodRepository.save(recordFound);
        });
    } */
/* 
    public boolean delete(@PathVariable  @NotNull @Positive String id){
        return foodRepository.findById(id)
        .map(recordFound -> {
             foodRepository.deleteById(id);
             return true;
        })
        .orElse(false);
     } */
}

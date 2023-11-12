package com.crud.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.crudspring.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food,String> {
    
}

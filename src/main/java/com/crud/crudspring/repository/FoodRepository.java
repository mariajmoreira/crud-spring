package com.crud.crudspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crud.crudspring.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
}

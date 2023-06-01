package com.crud.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.crudspring.model.Alimento;

@Repository
public interface AlimentosRepository extends JpaRepository<Alimento,Long> {
    
}

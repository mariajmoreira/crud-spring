package com.crud.crudspring.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.crud.crudspring.model.Alimento;
import com.crud.crudspring.repository.AlimentosRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class AlimentoService {
    
    private final AlimentosRepository alimentosRepository;

    public AlimentoService(AlimentosRepository alimentosRepository){
        this.alimentosRepository = alimentosRepository;
    }

    public List<Alimento> list(){
        return alimentosRepository.findAll();
    }

    public Optional<Alimento> findById(@PathVariable  @NotNull @Positive Long id){
        return alimentosRepository.findById(id);
       // .map(recordFound -> ResponseEntity.ok().body(recordFound))
        //.orElse(ResponseEntity.notFound().build());
    }

   
    public Alimento create(@RequestBody @Valid Alimento alimento){
       return alimentosRepository.save(alimento);
    }


    public Optional<Alimento> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Alimento alimento){
        return alimentosRepository.findById(id)
        .map(recordFound -> {
            recordFound.setName(alimento.getName());
            recordFound.setCategory(alimento.getCategory());
            return alimentosRepository.save(recordFound);
        });
    }

    public boolean delete(@PathVariable  @NotNull @Positive Long id){
        return alimentosRepository.findById(id)
        .map(recordFound -> {
             alimentosRepository.deleteById(id);
             return true;
        })
        .orElse(false);
     }
}

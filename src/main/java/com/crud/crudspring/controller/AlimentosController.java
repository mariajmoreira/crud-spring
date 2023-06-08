package com.crud.crudspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crudspring.model.Alimento;
import com.crud.crudspring.repository.AlimentosRepository;
import com.crud.crudspring.service.AlimentoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/alimentos")
public class AlimentosController {

    private AlimentoService alimentoService;

    public AlimentosController(AlimentoService alimentoService) {
        this.alimentoService = alimentoService;
    }

    @GetMapping
    public List<Alimento> list(){
        return alimentoService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> findById(@PathVariable  @NotNull @Positive Long id){
        return alimentoService.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public Alimento create(@RequestBody @Valid Alimento alimento){
       return alimentoService.create(alimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alimento> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Alimento alimento){
        return alimentoService.update(id,alimento)
        .map(recordFound -> {
            return ResponseEntity.ok().body(recordFound);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable  @NotNull @Positive Long id){
       if(alimentoService.delete(id)){
        return ResponseEntity.noContent().<Void>build();
       }
      return ResponseEntity.notFound().build();
    
    }
}

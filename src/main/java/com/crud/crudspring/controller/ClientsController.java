package com.crud.crudspring.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crudspring.model.Client;
import com.crud.crudspring.model.User;
import com.crud.crudspring.repository.ClientsRepository;
import com.crud.crudspring.service.ClientsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/clients")
public class ClientsController {

    private ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping
    public List<Client> list(){
        return clientsService.list();
    }

    @GetMapping("getClientById")
    public ResponseEntity<Client> findById(@RequestParam  @NotNull @Positive Long id){
        System.out.println("id: " + id);
        return clientsService.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

       @GetMapping("/getClientByUserId")
    public ResponseEntity<Client> findByUserId(@RequestParam  @NotNull @Positive Long userId){
        return clientsService.findByUserid(userId)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

     @GetMapping("/calculateIMC")
    public ResponseEntity<Float> calculateIMC(@RequestParam  @NotNull @Positive Long id,@RequestParam Float weight, @RequestParam Float height,@RequestParam Integer age, @RequestParam String gender){
        Float result = clientsService.calculateIMC(weight, height, age, gender);
        return ResponseEntity.ok().body(result);
    }

        @GetMapping("/calculateTMB")
    public ResponseEntity<Double> calculateTMB(@RequestParam BigDecimal weight, @RequestParam BigDecimal height,@RequestParam Integer age, @RequestParam String gender){
        System.out.println("calculating TMB...");
        Double result = clientsService.calculateTMB(weight, height, age, gender);
        return ResponseEntity.ok().body(result);
    }
    

    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public Client create(@RequestBody @Valid Client clients){
       return clientsService.create(clients);
    }

    @PutMapping("/updateClient/{id}")
    public ResponseEntity<Client> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Client client){
        return clientsService.update(id,client)
        .map(recordFound -> {
            return ResponseEntity.ok().body(recordFound);
        })
        .orElse(ResponseEntity.notFound().build());
    }

     @PutMapping("/updateClientUserId/{id}")
    public ResponseEntity<Client> updateUserId(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid User user){
        return clientsService.setUserId(user,id)
        .map(recordFound -> {
            return ResponseEntity.ok().body(recordFound);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable  @NotNull @Positive Long id){
       if(clientsService.delete(id)){
        return ResponseEntity.noContent().<Void>build();
       }
      return ResponseEntity.notFound().build();
    
    }
}

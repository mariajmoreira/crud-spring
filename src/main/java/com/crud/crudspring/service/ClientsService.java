package com.crud.crudspring.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.crud.crudspring.model.Client;
import com.crud.crudspring.model.User;
import com.crud.crudspring.repository.ClientsRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ClientsService {
    
    private final ClientsRepository clientsRepository;

    public ClientsService(ClientsRepository clientsRepository){
        this.clientsRepository = clientsRepository;
    }

    public List<Client> list(){
        return clientsRepository.findAll();
    }

    public Optional<Client> findById(@PathVariable  @NotNull @Positive Long id){
        return clientsRepository.findById(id);
       // .map(recordFound -> ResponseEntity.ok().body(recordFound))
        //.orElse(ResponseEntity.notFound().build());
    }

     public Optional<Client> findByUserid(@PathVariable  @NotNull @Positive Long userId){
        return clientsRepository.findByUserid(userId);
       // .map(recordFound -> ResponseEntity.ok().body(recordFound))
        //.orElse(ResponseEntity.notFound().build());
    }
   
    public Client create(@RequestBody @Valid Client client){
       return clientsRepository.save(client);
    }

    public  Optional<Client> setUserId(@RequestBody User user,@PathVariable  @NotNull @Positive Long id){
         return clientsRepository.findById(id)
        .map(recordFound -> {
            recordFound.setUserid(user.getId());
            return clientsRepository.save(recordFound);
        });
    }


    public Optional<Client> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Client clients){
        return clientsRepository.findById(id)
        .map(recordFound -> {
            recordFound.setWeight(clients.getWeight());
            recordFound.setHeight(clients.getHeight());
            recordFound.setGender(clients.getGender());
            recordFound.setBirthdate(clients.getBirthdate());
            recordFound.setUserid(clients.getUserid());
            recordFound.setFirstname(clients.getFirstname());
            recordFound.setLastname(clients.getLastname());
            return clientsRepository.save(recordFound);
        });
    }

    public boolean delete(@PathVariable  @NotNull @Positive Long id){
        return clientsRepository.findById(id)
        .map(recordFound -> {
             clientsRepository.deleteById(id);
             return true;
        })
        .orElse(false);
     }

     public Float calculateIMC(Float weight, Float height, Integer age, String gender){
        Float result;
        result = weight / ( height*height);
        return result;
     }

     public Double calculateTMB(BigDecimal weight, BigDecimal height, Integer age, String gender){
      
        Double result=0.0;
        if(gender.equals("male")){
            result= 88.362 + (13.397 * weight.doubleValue()) + (4.799 * height.doubleValue()) - (5.677 * age);
        }

        if(gender.equals("female")){
                result =  447.593 + (9.247 * weight.doubleValue()) + (3.098 * height.doubleValue()) - (4.330 * age);
        }
        

       
        return result;
     }

}

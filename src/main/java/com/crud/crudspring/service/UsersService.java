package com.crud.crudspring.service;


import java.util.List;
import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web.Client;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.crud.crudspring.model.User;
import com.crud.crudspring.repository.UsersRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class UsersService {
    
    private final UsersRepository usersRepository;



    public UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }


    public List<User> usersList(){
        return usersRepository.findAll();
    }

    public Optional<User> findById(@PathVariable  @NotNull @Positive Long id){
        return usersRepository.findById(id);
       // .map(recordFound -> ResponseEntity.ok().body(recordFound))
        //.orElse(ResponseEntity.notFound().build());
    }

    public Optional<User> findByEmailandPassword(String email, String password){
        return usersRepository.findByEmailAndPassword(email,password);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password){
        return usersRepository.findByUsernameAndPassword(username,password);
    }

    public User findByUsername(String username){
        return usersRepository.findByUsername(username);
    }

    public User create(@RequestBody @Valid User user){
       return usersRepository.save(user);
    }

     public void deleteAll(){
       usersRepository.deleteAll();
       
     } 

       public boolean delete(@PathVariable  @NotNull @Positive Long id){
        return usersRepository.findById(id)
        .map(recordFound -> {
             usersRepository.deleteById(id);
             return true;
        })
        .orElse(false);
     } 

/*     public User registerNewUserAccount(User newUser, Client client) throws  {
   
    Client newClient = new User();
    user.setFirstName(account.getFirstName());
    user.setLastName(account.getLastName());
    
    user.setPassword(passwordEncoder.encode(account.getPassword()));
    
    user.setEmail(account.getEmail());
    user.setRole(new Role(Integer.valueOf(1), user));
    return repository.save(user);
} */

public User checkUsername(String username){
    User user = this.usersRepository.findByUsername(username);
    return user;
}

public User checkEmail(String email){
 User user = this.usersRepository.findByEmail(email);
    return user;
}


/*     public Optional<Users> update(@PathVariable @NotNull @Positive String id, @RequestBody @Valid Users alimento){
        return usersRepository.findById(id)
        .map(recordFound -> {
            recordFound.setName(alimento.getName());
            recordFound.setCategory(alimento.getCategory());
            recordFound.setCalories(alimento.getCalories());
            recordFound.setCarbs(alimento.getCarbs());
            recordFound.setFats(alimento.getFats());
            recordFound.setProtein(alimento.getProtein());
            return usersRepository.save(recordFound);
        });
    } */
/* 
    public boolean delete(@PathVariable  @NotNull @Positive String id){
        return usersRepository.findById(id)
        .map(recordFound -> {
             usersRepository.deleteById(id);
             return true;
        })
        .orElse(false);
     } */
}

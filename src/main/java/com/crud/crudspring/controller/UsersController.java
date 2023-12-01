package com.crud.crudspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crudspring.model.Food;
import com.crud.crudspring.model.User;
import com.crud.crudspring.service.UsersService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UsersController {

    private UsersService userService;

     @Autowired
    private AuthenticationManager authenticationManager;

    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsersList() {
        System.out.println("getting users");
        List<User> users = userService.usersList();

        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable @NotNull @Positive Long id) {
        return userService.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/login")
    public ResponseEntity<User> findByProps(@RequestParam @NotNull String username,
            @RequestParam @NotNull String password) {
                String passwordEnc = passwordEncoder.encode(password);

        User user =  userService.findByUsername(username);

        return new ResponseEntity<User>(userService.create(user), HttpStatus.ACCEPTED);
        //return ResponseEntity.ok().body(user);

    }


        @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
                Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<String>("User login successfully!...", HttpStatus.OK);

    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody @Valid User user) {
        
        if (userService.checkUsername(user.getUsername()) != null) {
            return new ResponseEntity<String>("Username already Exists ", HttpStatus.ACCEPTED);

        } else {
            if (userService.checkEmail(user.getEmail()) != null) {
                return new ResponseEntity<String>("There's another account with that e-mail", HttpStatus.ACCEPTED);
            } else {
                String password = passwordEncoder.encode(user.getPassword());
                user.setPassword(password);
                return new ResponseEntity<User>(userService.create(user), HttpStatus.CREATED);
            }
 
        }

    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        this.userService.deleteAll();
        return ResponseEntity.noContent().<Void>build();

    }

}

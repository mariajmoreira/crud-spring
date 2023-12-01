package com.crud.crudspring.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
@GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id")
    private Long id;


    @NotBlank
    @NotNull
    @Column( name="username",nullable = false)
    private String username;

    @NotNull
    @Length(max=200)
    @Column(name="email",length = 200, nullable = false)
    private String email;

        
    @NotNull
    @Length(max=100)
    @Column(name="password",length = 100, nullable = false)
    private String password;

    @NotBlank
    @NotNull
    @Column( name="role",nullable = false)
    private String role;

    @NotNull
    @Length(max=1)
    @Column(name="status",length = 1, nullable = false)
    private String status="0";

}

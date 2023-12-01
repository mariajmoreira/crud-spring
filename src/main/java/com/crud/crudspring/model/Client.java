package com.crud.crudspring.model;

import java.sql.Date;

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
@Table(name="client")
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
@GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id")
    private Long id;


    @NotNull
    @Column( name="userid",nullable = false)
    private Long userid;


    @NotBlank
    @NotNull
    @Column( name="firstname",nullable = false)
    private String firstname;

    @NotBlank
    @NotNull
    @Column( name="lastname",nullable = false)
    private String lastname;

    @NotNull
    @Length(max=200)
    @Column(name="weight",length = 200, nullable = false)
    private String weight;

        
    @NotNull
    @Length(max=10)
    @Column(name="height",length = 10, nullable = false)
    private String height;

    @NotBlank
    @NotNull
    @Column( name="birthdate",nullable = false)
    private String birthdate;

    @NotBlank
    @NotNull
    @Column( name="gender",nullable = false)
    private String gender;


    @NotBlank
    @NotNull
    @Column( name="type",nullable = false)
    private String type;



}

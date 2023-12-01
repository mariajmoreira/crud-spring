package com.crud.crudspring.model;

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
import lombok.Data;

@Data
@Entity
@Table(name="calculations")
@SQLDelete(sql="UPDATE Alimento SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")

public class Calculations {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column( name="expression",nullable = false)
    private String expression;

    @NotNull
    @Length(max=200)
    @Column(name="type",length = 200, nullable = false)
    private String type;

        
    @NotNull
    @Length(max=200)
    @Column(name="gender",length = 200, nullable = false)
    private String gender;

    @NotNull
    @Length(max=10)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(name="status",length = 10, nullable = false)
    private String status="Ativo";

}

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
@Table(name="insa_tca")
//@SQLDelete(sql="UPDATE Alimento SET status = 'Inativo' WHERE id = ?")
//@Where(clause = "status = 'Ativo'")
public class InsaFood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Cod")
    private String id;

    @NotBlank
    @NotNull
    @Length(min = 5, max=100)
    @Column(name="Nome", length = 100, nullable = false)
    private String name;

    @NotNull
    @Length(max=200)
    @Column(name="Nivel_1",length = 200, nullable = false)
    private String category;

    @NotNull
    @Length(max=200)
    @Column(name="Energia_kcal",length = 200, nullable = false)
    private String calories;

    @NotNull
    @Length(max=200)
    @Column(name="Hidratos_de_carbono", length = 200, nullable = false)
    private String carbs;

    @NotNull
    @Length(max=200)
    @Column(name="Proteinas",length = 200, nullable = false)
    private String protein;

    @NotNull
    @Length(max=200)
    @Column(name="Lipidos",length = 200, nullable = false)
    private String fats;

    @NotNull
    @Length(max=200)
    @Column(name="Quantidade",length = 200, nullable = false)
    private String quantidade;
    

}

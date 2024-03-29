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
@Table(name="food")
@AllArgsConstructor
@NoArgsConstructor
//@SQLDelete(sql="UPDATE Alimento SET status = 'Inativo' WHERE id = ?")
//@Where(clause = "status = 'Ativo'")
public class Food {



    @Id
   @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
@GenericGenerator(name = "seq", strategy="increment")
    @Column(name="id")
    private Long id;

    @NotBlank
    @NotNull
    @Column(name="name",nullable = false)
    private String name;

    @NotNull
    @Length(max=200)
    @Column(name="category",length = 200, nullable = false)
    private String category;

    @NotNull
    @Length(max=200)
    @Column(name="calories",length = 200, nullable = false)
    private String calories;

    @NotNull
    @Length(max=200)
    @Column(name="carbs", length = 200, nullable = false)
    private String carbs;

    @NotNull
    @Length(max=200)
    @Column(name="protein",length = 200, nullable = false)
    private String protein;

    @NotNull
    @Length(max=200)
    @Column(name="fats",length = 200, nullable = false)
    private String fats;

    @NotNull
    @Length(max=200)
    @Column(name="quantidade",length = 200, nullable = false)
    private String quantidade;

        public Food(InsaFood insa) {
        //this.id=insa.getId();
        this.category=insa.getCategory();
        this.name = insa.getName();
        this.calories = insa.getCalories();
        this.carbs=insa.getCarbs();
        this.protein = insa.getProtein();
        this.fats=insa.getFats();
        this.quantidade=insa.getQuantidade();
    }


    

}

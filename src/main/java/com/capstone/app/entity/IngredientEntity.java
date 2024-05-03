package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_INGREDIENTS")
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_INGREDIENTS_SEQ")
    @SequenceGenerator(name = "G_INGREDIENTS_SEQ", sequenceName = "G_INGREDIENTS_SEQ", allocationSize = 1)
    @Column(name = "INGREDIENT_ID")
    private int ingredientId;

    @Column(name = "INGREDIENT_NAME")
    private String ingredientName;

    @OneToMany(mappedBy = "ingredient")
    @JsonIgnore
    private List<RecipeIngredientEntity> recipeIngredients = new ArrayList<>();
}

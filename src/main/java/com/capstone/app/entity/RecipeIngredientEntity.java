package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_RECIPE_INGREDIENTS")
public class RecipeIngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_RECIPE_INGREDIENTS_SEQ")
    @SequenceGenerator(name = "G_RECIPE_INGREDIENTS_SEQ", sequenceName = "G_RECIPE_INGREDIENTS_SEQ", allocationSize = 1)
    @Column(name = "RECIPE_INGREDIENT_ID")
    @Getter
    @Setter
    private int recipeIngredientId;

    @Column(name = "RECIPE_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private int recipeId;

    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    @Getter
    @Setter
    @ToString.Exclude
    @JsonIgnore
    private RecipeEntity recipe;

    @Column(name = "INGREDIENT_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private int ingredientId;

    @ManyToOne
    @JoinColumn(name = "INGREDIENT_ID")
    @Getter
    @Setter
    @ToString.Exclude
    private IngredientEntity ingredient;

    @Column(name = "INGREDIENT_QUANTITY")
    @Getter
    @Setter
    private String ingredientQuantity;

}

package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_DIET_RECIPES")
public class DietRecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_DIET_RECIPES_SEQ")
    @SequenceGenerator(name = "G_DIET_RECIPES_SEQ", sequenceName = "G_DIET_RECIPES_SEQ", allocationSize = 1)
    @Column(name = "DIET_RECIPE_ID")
    @Getter
    @Setter
    private int dietRecipeId;
    @Column(name = "RECIPE_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private int recipeId;
    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    @Getter
    @Setter
    @ToString.Exclude
    private RecipeEntity recipe;
    @Column(name = "DIET_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private int dietId;
    @ManyToOne
    @JoinColumn(name = "DIET_ID")
    @Getter
    @Setter
    @JsonIgnore
    @ToString.Exclude
    private DietEntity diet;
    @Column(name = "DAY")
    @Getter
    @Setter
    private int day;
    @Column(name = "RECIPE_ORDER")
    @Getter
    @Setter
    private int recipeOrder;
}

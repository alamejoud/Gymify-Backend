package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_RECIPE_CATEGORIES")
public class RecipeCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_RECIPE_CATEGORIES_SEQ")
    @SequenceGenerator(name = "G_RECIPE_CATEGORIES_SEQ", sequenceName = "G_RECIPE_CATEGORIES_SEQ", allocationSize = 1)
    @Column(name = "RECIPE_CATEGORY_ID")
    @Getter
    @Setter
    private int recipeCategoryId;

    @Column(name = "RECIPE_CATEGORY")
    @Getter
    @Setter
    private String recipeCategory;

    @OneToMany(mappedBy = "recipeCategory")
    @Getter
    @Setter
    @ToString.Exclude
    @JsonIgnore
    private List<RecipeEntity> recipes = new ArrayList<>();

}

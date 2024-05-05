package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_RECIPES")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_RECIPES_SEQ")
    @SequenceGenerator(name = "G_RECIPES_SEQ", sequenceName = "G_RECIPES_SEQ", allocationSize = 1)
    @Column(name = "RECIPE_ID")
    @Getter
    @Setter
    private int recipeId;

    @Column(name = "EXTERNAL_RECIPE_ID")
    @Getter
    @Setter
    private int externalRecipeId;

    @Column(name = "RECIPE_NAME")
    @Getter
    @Setter
    private String recipeName;

    @Column(name = "COOK_TIME")
    @Getter
    @Setter
    private String cookTime;

    @Column(name = "PREP_TIME")
    @Getter
    @Setter
    private String prepTime;

    @Column(name = "TOTAL_TIME")
    @Getter
    @Setter
    private String totalTime;

    @Column(name = "PUBLISHED_DATE")
    @Getter
    @Setter
    private String publishedDate;

    @Column(name = "DESCRIPTION")
    @Getter
    @Setter
    private String description;

    @Column(name = "AGGREGATED_RATING")
    @Getter
    @Setter
    private double aggregatedRating;

    @Column(name = "REVIEW_COUNT")
    @Getter
    @Setter
    private int reviewCount;

    @Column(name = "CALORIES")
    @Getter
    @Setter
    private double calories;

    @Column(name = "FAT_CONTENT")
    @Getter
    @Setter
    private double fatContent;

    @Column(name = "SATURATED_FAT_CONTENT")
    @Getter
    @Setter
    private double saturatedFatContent;

    @Column(name = "CHOLESTEROL_CONTENT")
    @Getter
    @Setter
    private double cholesterolContent;

    @Column(name = "SODIUM_CONTENT")
    @Getter
    @Setter
    private double sodiumContent;

    @Column(name = "CARBOHYDRATE_CONTENT")
    @Getter
    @Setter
    private double carbohydrateContent;

    @Column(name = "FIBER_CONTENT")
    @Getter
    @Setter
    private double fiberContent;

    @Column(name = "SUGAR_CONTENT")
    @Getter
    @Setter
    private double sugarContent;

    @Column(name = "PROTEIN_CONTENT")
    @Getter
    @Setter
    private double proteinContent;

    @Column(name = "RECIPE_SERVINGS")
    @Getter
    @Setter
    private int recipeServings;

    @Column(name = "RECIPE_YIELD")
    @Getter
    @Setter
    private String recipeYield;

    @ManyToOne
    @JoinColumn(name = "RECIPE_CATEGORY_ID")
    @Getter
    @Setter
    @ToString.Exclude
    private RecipeCategoryEntity recipeCategory;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    @Getter
    @Setter
    @ToString.Exclude
    private UserEntity createdBy;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="recipe")
    @Getter
    @Setter
    private List<RecipeImageEntity> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="recipe")
    @Getter
    @Setter
    private List<RecipeInstructionEntity> instructions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="recipe")
    @Getter
    @Setter
    private List<RecipeIngredientEntity> ingredients = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "G_RECIPE_KEYWORDS",
            joinColumns = { @JoinColumn(name = "RECIPE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "KEYWORD_ID") }
    )
    @Getter
    private List<KeywordEntity> keywords = new ArrayList<>();

    @OneToMany(mappedBy = "recipe")
    @Getter
    @Setter
    @JsonIgnore
    private List<DietRecipeEntity> dietRecipes = new ArrayList<>();

    public void setKeywords(List<KeywordEntity> keywords) {
        this.keywords.clear();
        keywords.forEach(keyword -> this.keywords.add(keyword));
        keywords.forEach(keyword -> keyword.getRecipes().add(this));
    }
}

package com.capstone.app.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_RECIPES_STA")
public class RecipeSTAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_RECIPES_STA_SEQ")
    @SequenceGenerator(name = "G_RECIPES_STA_SEQ", sequenceName = "G_RECIPES_STA_SEQ", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "RECIPEID")
    private String recipeId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHORID")
    private String authorId;

    @Column(name = "AUTHORNAME")
    private String authorName;

    @Column(name = "COOKTIME")
    private String cookTime;

    @Column(name = "PREPTIME")
    private String prepTime;

    @Column(name = "TOTALTIME")
    private String totalTime;

    @Column(name = "DATEPUBLISHED")
    private String datePublished;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGES")
    private String images;

    @Column(name = "RECIPECATEGORY")
    private String recipeCategory;

    @Column(name = "KEYWORDS")
    private String keywords;

    @Column(name = "RECIPEINGREDIENTQUANTITIES")
    private String recipeIngredientQuantities;

    @Column(name = "RECIPEINGREDIENTPARTS")
    private String recipeIngredientParts;

    @Column(name = "AGGREGATEDRATING")
    private String aggregatedRating;

    @Column(name = "REVIEWCOUNT")
    private String reviewCount;

    @Column(name = "CALORIES")
    private String calories;

    @Column(name = "FATCONTENT")
    private String fatContent;

    @Column(name = "SATURATEDFATCONTENT")
    private String saturatedFatContent;

    @Column(name = "CHOLESTEROLCONTENT")
    private String cholesterolContent;

    @Column(name = "SODIUMCONTENT")
    private String sodiumContent;

    @Column(name = "CARBOHYDRATECONTENT")
    private String carbohydrateContent;

    @Column(name = "FIBERCONTENT")
    private String fiberContent;

    @Column(name = "SUGARCONTENT")
    private String sugarContent;

    @Column(name = "PROTEINCONTENT")
    private String proteinContent;

    @Column(name = "RECIPESERVINGS")
    private String recipeServings;

    @Column(name = "RECIPEYIELD")
    private String recipeYield;

    @Column(name = "RECIPEINSTRUCTIONS")
    private String recipeInstructions;

}

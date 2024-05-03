package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_RECIPE_IMAGES")
public class RecipeImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_RECIPE_IMAGES_SEQ")
    @SequenceGenerator(name = "G_RECIPE_IMAGES_SEQ", sequenceName = "G_RECIPE_IMAGES_SEQ", allocationSize = 1)
    @Column(name = "RECIPE_IMAGE_ID")
    private int recipeImageId;

    @Column(name = "IMAGE_SRC")
    private String imageSrc;

    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    @JsonIgnore
    @ToString.Exclude
    private RecipeEntity recipe;
}

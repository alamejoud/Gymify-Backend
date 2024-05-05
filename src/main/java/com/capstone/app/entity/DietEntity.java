package com.capstone.app.entity;


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
@Table(name = "G_DIETS")
public class DietEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_DIETS_SEQ")
    @SequenceGenerator(name = "G_DIETS_SEQ", sequenceName = "G_DIETS_SEQ", allocationSize = 1)
    @Column(name = "DIET_ID")
    @Getter
    @Setter
    private int dietId;
    @Column(name = "DIET_NAME")
    @Getter
    @Setter
    private String dietName;
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    @Getter
    @Setter
    private UserEntity createdBy;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DIET_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 1")
    @OrderBy("recipeOrder ASC")
    private List<DietRecipeEntity> mondayRecipes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DIET_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 2")
    @OrderBy("recipeOrder ASC")
    private List<DietRecipeEntity> tuesdayRecipes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DIET_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 3")
    @OrderBy("recipeOrder ASC")
    private List<DietRecipeEntity> wednesdayRecipes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DIET_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 4")
    @OrderBy("recipeOrder ASC")
    private List<DietRecipeEntity> thursdayRecipes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DIET_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 5")
    @OrderBy("recipeOrder ASC")
    private List<DietRecipeEntity> fridayRecipes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DIET_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 6")
    @OrderBy("recipeOrder ASC")
    private List<DietRecipeEntity> saturdayRecipes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DIET_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 7")
    @OrderBy("recipeOrder ASC")
    private List<DietRecipeEntity> sundayRecipes = new ArrayList<>();
    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "G_USER_DIETS",
            joinColumns = { @JoinColumn(name = "DIET_ID") },
            inverseJoinColumns = { @JoinColumn(name = "USER_ID") }
    )
    @Getter
    @Setter
    private List<UserEntity> users = new ArrayList<>();
}

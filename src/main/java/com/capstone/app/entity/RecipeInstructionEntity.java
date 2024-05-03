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
@Table(name = "G_RECIPE_INSTRUCTIONS")
public class RecipeInstructionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_RECIPE_INSTRUCTIONS_SEQ")
    @SequenceGenerator(name = "G_RECIPE_INSTRUCTIONS_SEQ", sequenceName = "G_RECIPE_INSTRUCTIONS_SEQ", allocationSize = 1)
    @Column(name = "RECIPE_INSTRUCTION_ID")
    private int recipeInstructionId;

    @Column(name = "INSTRUCTION")
    private String instruction;

    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    @JsonIgnore
    @ToString.Exclude
    private RecipeEntity recipe;
}

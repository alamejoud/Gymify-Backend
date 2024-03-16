package com.capstone.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_EXERCISE_TYPES")
public class ExerciseTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_EXERCISE_TYPES_SEQ")
    @SequenceGenerator(name = "G_EXERCISE_TYPES_SEQ", sequenceName = "G_EXERCISE_TYPES_SEQ", allocationSize = 1)
    @Column(name = "EQUIPMENT_ID")
    private int typeId;
    @Column(name = "EXERCISE_TYPE")
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_ID")
    private ExerciseEntity exercise;
}

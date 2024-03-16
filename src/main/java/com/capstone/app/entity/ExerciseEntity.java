package com.capstone.app.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_EXERCISES")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_EXERCISES_SEQ")
    @SequenceGenerator(name = "G_EXERCISES_SEQ", sequenceName = "G_EXERCISES_SEQ", allocationSize = 1)
    @Column(name = "EXERCISE_ID")
    private int exerciseId;
    @Column(name = "EXERCISE_NAME")
    private String exerciseName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAJOR_MUSCLE_ID")
    private MuscleEntity majorMuscle;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "MODIFICATIONS")
    private String modifications;
    @OneToMany(fetch = FetchType.LAZY, mappedBy="exercise")
    private Set<ExerciseEquipmentEntity> equipments;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "G_EXERCISE_MINOR_MUSCLES",
            joinColumns = { @JoinColumn(name = "EXERCISE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "MINOR_MUSCLE_ID") }
    )
    private Set<MuscleEntity> minorMuscles;
    @OneToMany(fetch = FetchType.LAZY, mappedBy="exercise")
    private Set<ExerciseTypeEntity> types;
}

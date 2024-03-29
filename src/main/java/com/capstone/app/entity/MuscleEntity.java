package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_MUSCLES")
public class MuscleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_MUSCLES_SEQ")
    @SequenceGenerator(name = "G_MUSCLES_SEQ", sequenceName = "G_MUSCLES_SEQ", allocationSize = 1)
    @Column(name = "MUSCLE_ID")
    private int muscleId;
    @Column(name = "MUSCLE_NAME")
    private String muscleName;
    @Lob
    @Basic
    @Column(name = "MUSCLE_PICTURE", columnDefinition = "BLOB")
    private byte[] musclePicture;
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "minorMuscles")
    private Set<ExerciseEntity> minorExercises = new HashSet<>();
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "majorMuscle")
    private List<ExerciseEntity> majorExercises = new ArrayList<>();
}

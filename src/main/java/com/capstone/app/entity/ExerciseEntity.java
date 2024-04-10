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
@Table(name = "G_EXERCISES")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_EXERCISES_SEQ")
    @SequenceGenerator(name = "G_EXERCISES_SEQ", sequenceName = "G_EXERCISES_SEQ", allocationSize = 1)
    @Column(name = "EXERCISE_ID")
    @Getter
    @Setter
    private int exerciseId;
    @Column(name = "EXERCISE_NAME")
    @Getter
    @Setter
    private String exerciseName;
    @ManyToOne
    @JoinColumn(name = "MAJOR_MUSCLE_ID")
    @Getter
    @Setter
    private MuscleEntity majorMuscle;
    @Column(name = "NOTES")
    @Getter
    @Setter
    private String notes;
    @Column(name = "MODIFICATIONS")
    @Getter
    @Setter
    private String modifications;
    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(
            name = "G_EXERCISE_Equipments",
            joinColumns = { @JoinColumn(name = "EXERCISE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "EQUIPMENT_ID") }
    )
    @Getter
    private List<EquipmentEntity> equipments = new ArrayList<>();
    @ToString.Exclude
    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(
            name = "G_EXERCISE_MINOR_MUSCLES",
            joinColumns = { @JoinColumn(name = "EXERCISE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "MINOR_MUSCLE_ID") }
    )
    @Getter
    private List<MuscleEntity> minorMuscles = new ArrayList<>();
    @ToString.Exclude
    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(
            name = "G_EXERCISE_TYPES",
            joinColumns = { @JoinColumn(name = "EXERCISE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "TYPE_ID") }
    )
    @Getter
    private List<TypeEntity> types = new ArrayList<>();
    @Column(name = "VIDEO_LINK")
    @Getter
    @Setter
    private String videoLink;
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    @Getter
    @Setter
    private UserEntity createdBy;
    @OneToMany(mappedBy = "exercise")
    @Getter
    @Setter
    @JsonIgnore
    private List<WorkoutExerciseEntity> workoutExercises = new ArrayList<>();

    public void setEquipments(List<EquipmentEntity> equipments) {
        this.equipments.clear();
        equipments.forEach(equipment -> this.equipments.add(equipment));
        equipments.forEach(equipment -> equipment.getExercises().add(this));
    }
    public void setMinorMuscles(List<MuscleEntity> minorMuscles) {
        this.minorMuscles.clear();
        minorMuscles.forEach(muscle -> this.minorMuscles.add(muscle));
        minorMuscles.forEach(muscle -> muscle.getMinorExercises().add(this));
    }
    public void setTypes(List<TypeEntity> types) {
        this.types.clear();
        types.forEach(type -> this.types.add(type));
        types.forEach(type -> type.getExercises().add(this));
    }

}

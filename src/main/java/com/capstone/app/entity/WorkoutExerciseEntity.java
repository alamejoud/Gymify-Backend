package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_WORKOUT_EXERCISES")
public class WorkoutExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_WORKOUT_EXERCISES_SEQ")
    @SequenceGenerator(name = "G_WORKOUT_EXERCISES_SEQ", sequenceName = "G_WORKOUT_EXERCISES_SEQ", allocationSize = 1)
    @Column(name = "WORKOUT_EXERCISE_ID")
    @Getter
    @Setter
    private int workoutExerciseId;
    @Column(name = "EXERCISE_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private int exerciseId;
    @ManyToOne
    @JoinColumn(name = "EXERCISE_ID")
    @Getter
    @Setter
    @ToString.Exclude
    private ExerciseEntity exercise;
    @Column(name = "WORKOUT_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private int workoutId;
    @ManyToOne
    @JoinColumn(name = "WORKOUT_ID")
    @Getter
    @Setter
    @JsonIgnore
    @ToString.Exclude
    private WorkoutEntity workout;
    @Column(name = "DAY")
    @Getter
    @Setter
    private int day;

}

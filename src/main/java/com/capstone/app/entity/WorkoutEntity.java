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
@Table(name = "G_WORKOUTS")
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_WORKOUTS_SEQ")
    @SequenceGenerator(name = "G_WORKOUTS_SEQ", sequenceName = "G_WORKOUTS_SEQ", allocationSize = 1)
    @Column(name = "WORKOUT_ID")
    @Getter
    @Setter
    private int workoutId;
    @Column(name = "WORKOUT_NAME")
    @Getter
    @Setter
    private String workoutName;
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    @Getter
    @Setter
    private UserEntity createdBy;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "WORKOUT_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 1")
    private List<WorkoutExerciseEntity> mondayExercises = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "WORKOUT_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 2")
    private List<WorkoutExerciseEntity> tuesdayExercises = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "WORKOUT_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 3")
    private List<WorkoutExerciseEntity> wednesdayExercises = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "WORKOUT_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 4")
    private List<WorkoutExerciseEntity> thursdayExercises = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "WORKOUT_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 5")
    private List<WorkoutExerciseEntity> fridayExercises = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "WORKOUT_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 6")
    private List<WorkoutExerciseEntity> saturdayExercises = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "WORKOUT_ID")
    @Getter
    @Setter
    @Where(clause = "DAY = 7")
    private List<WorkoutExerciseEntity> sundayExercises = new ArrayList<>();
    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "G_USER_WORKOUTS",
            joinColumns = { @JoinColumn(name = "WORKOUT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "USER_ID") }
    )
    @Getter
    @Setter
    private List<UserEntity> users = new ArrayList<>();
}

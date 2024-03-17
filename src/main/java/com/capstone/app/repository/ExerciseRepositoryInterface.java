package com.capstone.app.repository;

import com.capstone.app.entity.*;

import java.io.IOException;
import java.util.List;

public interface ExerciseRepositoryInterface {

    public List<MuscleEntity> getExerciseMuscles();
    public List<EquipmentEntity> getExerciseEquipments();
    public List<TypeEntity> getExerciseTypes();
    public List<ExerciseEntity> getExerciseList(String muscleId);
    public List<LookupEntity> getExerciseGroups();
    public List<ExerciseEntity> filterExercises(String exerciseName);
}

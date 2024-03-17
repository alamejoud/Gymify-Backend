package com.capstone.app.service;

import com.capstone.app.entity.*;

import java.util.List;

public interface ExerciseServiceInterface {
    public List<MuscleEntity> getExerciseMuscles();
    public List<EquipmentEntity> getExerciseEquipments();
    public List<TypeEntity> getExerciseTypes();
    public List<ExerciseEntity> getExerciseList(String muscleId);
    public List<LookupEntity> getExerciseGroups();
    public List<ExerciseEntity> filterExercises(String exerciseName);
}

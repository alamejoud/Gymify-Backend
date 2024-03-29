package com.capstone.app.service;

import com.capstone.app.entity.*;

import java.util.List;

public interface ExerciseServiceInterface {
    public List<MuscleEntity> getExerciseMuscles();
    public List<EquipmentEntity> getExerciseEquipments();
    public List<TypeEntity> getExerciseTypes();
    public List<ExerciseEntity> getExerciseByMuscle(String muscleId, String token);
    public List<ExerciseEntity> getExerciseByEquipment(String equipmentId, String token);
    public List<ExerciseEntity> getExerciseByType(String typeId, String token);
    public List<ExerciseEntity> getExerciseBySearch(String search, String token);
    public List<LookupEntity> getExerciseGroups();
    public List<ExerciseEntity> filterExercises(String exerciseName, String token);
    public List<MuscleEntity> filterMuscles(String muscleName);
    public List<EquipmentEntity> filterEquipments(String equipmentName);
    public List<TypeEntity> filterTypes(String typeName);
    public String getMuscleName(String muscleId);
    public String getEquipmentName(String equipmentId);
    public String getTypeName(String typeId);
    public void saveExercise(ExerciseEntity exercise, String token);
    public void deleteExercise(int exerciseId);
    public ExerciseEntity getExerciseById(int equipmentId);
}

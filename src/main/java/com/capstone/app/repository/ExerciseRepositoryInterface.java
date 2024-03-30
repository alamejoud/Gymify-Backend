package com.capstone.app.repository;

import com.capstone.app.entity.*;

import java.io.IOException;
import java.util.List;

public interface ExerciseRepositoryInterface {

    public List<MuscleEntity> getExerciseMuscles();
    public List<EquipmentEntity> getExerciseEquipments();
    public List<TypeEntity> getExerciseTypes();
    public List<ExerciseEntity> getExerciseByMuscle(String muscleId, String username, String role);
    public List<ExerciseEntity> getExerciseByEquipment(String equipmentId, String username, String role);
    public List<ExerciseEntity> getExerciseByType(String typeId, String username, String role);
    public List<ExerciseEntity> getExerciseBySearch(String search, String username, String role);
    public List<LookupEntity> getExerciseGroups();
    public List<ExerciseEntity> filterExercises(String exerciseName, String username, String role);
    public List<MuscleEntity> filterMuscles(String muscleName);
    public List<EquipmentEntity> filterEquipments(String equipmentName);
    public List<TypeEntity> filterTypes(String typeName);
    public String getMuscleName(String muscleId);
    public String getEquipmentName(String equipmentId);
    public String getTypeName(String typeId);
    public EquipmentEntity getEquipmentById(int equipmentId);
    public void saveExercise(ExerciseEntity exercise);
    public void deleteExercise(int exerciseId);
    public ExerciseEntity getExerciseById(int equipmentId);
    public List<ExerciseEntity> getExerciseList();
}

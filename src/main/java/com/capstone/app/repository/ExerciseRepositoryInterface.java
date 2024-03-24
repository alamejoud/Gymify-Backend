package com.capstone.app.repository;

import com.capstone.app.entity.*;

import java.io.IOException;
import java.util.List;

public interface ExerciseRepositoryInterface {

    public List<MuscleEntity> getExerciseMuscles();
    public List<EquipmentEntity> getExerciseEquipments();
    public List<TypeEntity> getExerciseTypes();
    public List<ExerciseEntity> getExerciseByMuscle(String muscleId);
    public List<ExerciseEntity> getExerciseByEquipment(String equipmentId);
    public List<ExerciseEntity> getExerciseByType(String typeId);
    public List<ExerciseEntity> getExerciseBySearch(String search);
    public List<LookupEntity> getExerciseGroups();
    public List<ExerciseEntity> filterExercises(String exerciseName);
    public String getMuscleName(String muscleId);
    public String getEquipmentName(String equipmentId);
    public String getTypeName(String typeId);
}

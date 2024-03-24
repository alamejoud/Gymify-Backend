package com.capstone.app.service;

import com.capstone.app.entity.*;
import com.capstone.app.repository.ExerciseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService implements ExerciseServiceInterface{
    @Autowired
    private ExerciseRepositoryInterface exerciseRepository;
    @Override
    public List<MuscleEntity> getExerciseMuscles() {
            return exerciseRepository.getExerciseMuscles();
    }

    @Override
    public List<EquipmentEntity> getExerciseEquipments() {
        return exerciseRepository.getExerciseEquipments();
    }

    @Override
    public List<TypeEntity> getExerciseTypes() {
        return exerciseRepository.getExerciseTypes();
    }

    @Override
    public List<ExerciseEntity> getExerciseByMuscle(String muscleId) {
        return exerciseRepository.getExerciseByMuscle(muscleId);
    }

    @Override
    public List<ExerciseEntity> getExerciseByEquipment(String equipmentId) {
        return exerciseRepository.getExerciseByEquipment(equipmentId);
    }

    @Override
    public List<ExerciseEntity> getExerciseByType(String typeId) {
        return exerciseRepository.getExerciseByType(typeId);
    }

    @Override
    public List<ExerciseEntity> getExerciseBySearch(String search) {
        return exerciseRepository.getExerciseBySearch(search);
    }

    @Override
    public List<LookupEntity> getExerciseGroups() {
        return exerciseRepository.getExerciseGroups();
    }

    @Override
    public List<ExerciseEntity> filterExercises(String exerciseName) {
        return exerciseRepository.filterExercises(exerciseName);
    }

    @Override
    public String getMuscleName(String muscleId) {
       return exerciseRepository.getMuscleName(muscleId);
    }

    @Override
    public String getEquipmentName(String equipmentId) {
        return exerciseRepository.getEquipmentName(equipmentId);
    }

    @Override
    public String getTypeName(String typeId) {
        return exerciseRepository.getTypeName(typeId);
    }
}

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
    public List<ExerciseEntity> getExerciseList(String muscleId) {
        return exerciseRepository.getExerciseList(muscleId);
    }

    @Override
    public List<LookupEntity> getExerciseGroups() {
        return exerciseRepository.getExerciseGroups();
    }

    @Override
    public List<ExerciseEntity> filterExercises(String exerciseName) {
        return exerciseRepository.filterExercises(exerciseName);
    }
}

package com.capstone.app.service;

import com.capstone.app.entity.ExerciseEntity;
import com.capstone.app.entity.MuscleEntity;
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
    public List<ExerciseEntity> getExerciseList(String muscleId) {
        return exerciseRepository.getExerciseList(muscleId);
    }
}

package com.capstone.app.service;

import com.capstone.app.entity.ExerciseEntity;
import com.capstone.app.entity.MuscleEntity;

import java.util.List;

public interface ExerciseServiceInterface {
    public List<MuscleEntity> getExerciseMuscles();
    public List<ExerciseEntity> getExerciseList(String muscleId);
}

package com.capstone.app.repository;

import com.capstone.app.entity.ExerciseEntity;
import com.capstone.app.entity.MuscleEntity;

import java.io.IOException;
import java.util.List;

public interface ExerciseRepositoryInterface {

    public List<MuscleEntity> getExerciseMuscles();
    public List<ExerciseEntity> getExerciseList(String muscleId);
}

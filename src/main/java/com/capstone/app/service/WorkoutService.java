package com.capstone.app.service;

import com.capstone.app.entity.WorkoutEntity;
import com.capstone.app.repository.WorkoutRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService implements WorkoutServiceInterface{

    @Autowired
    private WorkoutRepositoryInterface workoutRepository;

    @Override
    public WorkoutEntity getWorkoutById(int id) {
        return workoutRepository.getWorkoutById(id);
    }
}

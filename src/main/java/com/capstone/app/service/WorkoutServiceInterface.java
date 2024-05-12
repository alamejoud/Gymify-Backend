package com.capstone.app.service;

import com.capstone.app.entity.WorkoutEntity;

import java.util.List;

public interface WorkoutServiceInterface {

    public WorkoutEntity getWorkoutById(int id);
    public void saveWorkout(WorkoutEntity workout, String token);
    public List<WorkoutEntity> getMyWorkouts(String token, boolean onlyCreatedWorkout);
    public void deleteWorkout(int workoutId);
    public List<WorkoutEntity> getTrainersWorkouts(String search);
    public void subscribeToWorkout(int workoutId, String token);
    public void unsubscribeFromWorkout(int workoutId, String token);
    public List<WorkoutEntity> getMyWorkoutNames(String token);
}

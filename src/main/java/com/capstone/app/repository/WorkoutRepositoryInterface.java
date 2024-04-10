package com.capstone.app.repository;

import com.capstone.app.entity.WorkoutEntity;

import java.util.List;

public interface WorkoutRepositoryInterface {

    public WorkoutEntity getWorkoutById(int id);
    public void saveWorkout(WorkoutEntity workout);
    public List<WorkoutEntity> getMyWorkouts(String username, boolean onlyCreatedWorkout);
    public void deleteWorkout(int workoutId);
    public List<WorkoutEntity> getTrainersWorkouts(String search);
    public void subscribeToWorkout(int workoutId, int userId);
    public void unsubscribeFromWorkout(int workoutId, int userId);

}

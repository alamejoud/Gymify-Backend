package com.capstone.app.service;

import com.capstone.app.entity.UserEntity;
import com.capstone.app.entity.WorkoutEntity;
import com.capstone.app.repository.WorkoutRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService implements WorkoutServiceInterface{

    @Autowired
    private WorkoutRepositoryInterface workoutRepository;
    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private JwtService jwtService;

    @Override
    public WorkoutEntity getWorkoutById(int id) {
        return workoutRepository.getWorkoutById(id);
    }

    @Override
    public void saveWorkout(WorkoutEntity workout, String token) {
        workout.setCreatedBy(userService.getUserByUsername(jwtService.extractUsername(token)));
        if (workout.getUsers().stream().noneMatch(user -> user.getUsername().toLowerCase().equals(jwtService.extractUsername(token).toLowerCase()))) {
            workout.getUsers().add(userService.getUserByUsername(jwtService.extractUsername(token)));
        }
        workout.getUsers().forEach(user -> {
            if (user.getWorkouts().stream().noneMatch(workoutEntity -> workoutEntity.getWorkoutId() == workout.getWorkoutId())) {
                user.getWorkouts().add(workout);
            }
        });
        workout.getMondayExercises().forEach(workoutExerciseEntity -> {
            workoutExerciseEntity.setWorkout(workout);
            workoutExerciseEntity.getExercise().getWorkoutExercises().add(workoutExerciseEntity);
        });
        workout.getTuesdayExercises().forEach(workoutExerciseEntity -> {
            workoutExerciseEntity.setWorkout(workout);
            workoutExerciseEntity.getExercise().getWorkoutExercises().add(workoutExerciseEntity);
        });
        workout.getWednesdayExercises().forEach(workoutExerciseEntity -> {
            workoutExerciseEntity.setWorkout(workout);
            workoutExerciseEntity.getExercise().getWorkoutExercises().add(workoutExerciseEntity);
        });
        workout.getThursdayExercises().forEach(workoutExerciseEntity -> {
            workoutExerciseEntity.setWorkout(workout);
            workoutExerciseEntity.getExercise().getWorkoutExercises().add(workoutExerciseEntity);
        });
        workout.getFridayExercises().forEach(workoutExerciseEntity -> {
            workoutExerciseEntity.setWorkout(workout);
            workoutExerciseEntity.getExercise().getWorkoutExercises().add(workoutExerciseEntity);
        });
        workout.getSaturdayExercises().forEach(workoutExerciseEntity -> {
            workoutExerciseEntity.setWorkout(workout);
            workoutExerciseEntity.getExercise().getWorkoutExercises().add(workoutExerciseEntity);
        });
        workout.getSundayExercises().forEach(workoutExerciseEntity -> {
            workoutExerciseEntity.setWorkout(workout);
            workoutExerciseEntity.getExercise().getWorkoutExercises().add(workoutExerciseEntity);
        });
        workoutRepository.saveWorkout(workout);
    }

    @Override
    public List<WorkoutEntity> getMyWorkouts(String token, boolean onlyCreatedWorkout) {
        return workoutRepository.getMyWorkouts(jwtService.extractUsername(token), onlyCreatedWorkout);
    }

    @Override
    public void deleteWorkout(int workoutId) {
        WorkoutEntity workout = workoutRepository.getWorkoutById(workoutId);
        workoutRepository.deleteWorkout(workoutId);
    }

    @Override
    public List<WorkoutEntity> getTrainersWorkouts(String search) {
        return workoutRepository.getTrainersWorkouts(search);
    }

    @Override
    public void subscribeToWorkout(int workoutId, String token) {
       try {
           workoutRepository.subscribeToWorkout(workoutId, userService.getUserByUsername(jwtService.extractUsername(token)).getUserId());
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @Override
    public void unsubscribeFromWorkout(int workoutId, String token) {
        try {
            workoutRepository.unsubscribeFromWorkout(workoutId, userService.getUserByUsername(jwtService.extractUsername(token)).getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

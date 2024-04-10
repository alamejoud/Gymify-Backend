package com.capstone.app.controller;

import com.capstone.app.entity.ExerciseEntity;
import com.capstone.app.entity.WorkoutEntity;
import com.capstone.app.service.WorkoutServiceInterface;
import com.capstone.app.util.CommonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    @Autowired
    private WorkoutServiceInterface workoutService;

    @GetMapping("/getWorkoutById")
    public ResponseEntity<Object> getWorkoutById(@RequestParam int workoutId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("workout", workoutService.getWorkoutById(workoutId)));
    }

    @PostMapping("/saveWorkout")
    public ResponseEntity<Object> saveWorkout(@RequestBody WorkoutEntity workout, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        workoutService.saveWorkout(workout, token);
        return ResponseEntity.ok().body(Map.of("message", "Workout saved successfully."));
    }

    @GetMapping("/getMyWorkouts")
    public ResponseEntity<Object> getMyWorkouts(@RequestParam boolean onlyCreatedWorkout, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        List<WorkoutEntity> workouts = workoutService.getMyWorkouts(token, onlyCreatedWorkout);
        return ResponseEntity.ok().body(Map.of("workoutList", workouts));
    }

    @DeleteMapping("/deleteWorkout")
    public ResponseEntity<Object> deleteWorkout(@RequestParam int workoutId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        workoutService.deleteWorkout(workoutId);
        return ResponseEntity.ok().body(Map.of("message", "Workout deleted successfully."));
    }

    @GetMapping("/getTrainersWorkouts")
    public ResponseEntity<Object> getTrainersWorkouts(@RequestParam String search, @RequestParam int first, int rows, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        List<WorkoutEntity> workouts = workoutService.getTrainersWorkouts(search);
        if (first >= workouts.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "No Results Found"));
        }
        Map<String, Object> result = new HashMap<>();
        if (first + rows > workouts.size()) {
            result.put("workoutList", workouts.subList(first, workouts.size()));
            result.put("workoutListSize", workouts.size());
            return ResponseEntity.ok().body(result);
        }
        workouts.forEach(workout -> {
            workout.setUsers(new ArrayList<>());
        });
        result.put("workoutList", workouts.subList(first, first + rows));
        result.put("workoutListSize", workouts.size());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/subscribeToWorkout")
    public ResponseEntity<Object> subscribeToWorkout(@RequestParam int workoutId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        workoutService.subscribeToWorkout(workoutId, token);
        return ResponseEntity.ok().body(Map.of("message", "Subscribed to workout successfully."));
    }

    @GetMapping("/unsubscribeFromWorkout")
    public ResponseEntity<Object> unsubscribeFromWorkout(@RequestParam int workoutId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        workoutService.unsubscribeFromWorkout(workoutId, token);
        return ResponseEntity.ok().body(Map.of("message", "Unsubscribed from workout successfully."));
    }
}

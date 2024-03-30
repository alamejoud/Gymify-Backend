package com.capstone.app.controller;

import com.capstone.app.service.WorkoutServiceInterface;
import com.capstone.app.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

package com.capstone.app.controller;

import com.capstone.app.entity.ExerciseEntity;
import com.capstone.app.service.JwtService;
import com.capstone.app.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.capstone.app.service.ExerciseServiceInterface;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    @Autowired
    private ExerciseServiceInterface exerciseService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/getExerciseCategories")
    public ResponseEntity<Object> getExerciseCategories(@RequestHeader("Authorization") String token, @RequestParam String group) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        if (group.equals("muscle")) {
            return ResponseEntity.ok().body(Map.of("exerciseCategories", exerciseService.getExerciseMuscles()));
        } else if (group.equals("equipment")) {
            return ResponseEntity.ok().body(Map.of("exerciseCategories", exerciseService.getExerciseEquipments()));
        } else if (group.equals("type")) {
            return ResponseEntity.ok().body(Map.of("exerciseCategories", exerciseService.getExerciseTypes()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Invalid category"));
        }
    }

    @GetMapping("/getExerciseList")
    public ResponseEntity<Object> getExerciseList(@RequestParam String muscleId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.getExerciseList(muscleId)));
    }

    @GetMapping("/getExerciseGroups")
    public ResponseEntity<Object> getExerciseGroups(@RequestHeader("Authorization") String token){
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("exerciseGroups", exerciseService.getExerciseGroups()));
    }

    @GetMapping("filterExercises")
    public ResponseEntity<Object> filterExercises(@RequestParam String exerciseName, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.filterExercises(exerciseName)));
    }
}

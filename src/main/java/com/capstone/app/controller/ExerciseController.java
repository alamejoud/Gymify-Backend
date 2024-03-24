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

    @GetMapping("/getExercisesByMuscle")
    public ResponseEntity<Object> getExerciseListByMuscle(@RequestParam String muscleId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.getExerciseByMuscle(muscleId)));
    }

    @GetMapping("/getExercisesByEquipment")
    public ResponseEntity<Object> getExerciseListByEquipment(@RequestParam String equipmentId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.getExerciseByEquipment(equipmentId)));
    }

    @GetMapping("/getExercisesByType")
    public ResponseEntity<Object> getExerciseListByType(@RequestParam String typeId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.getExerciseByType(typeId)));
    }

    @GetMapping("/getExercisesBySearch")
    public ResponseEntity<Object> getExerciseListBySearch(@RequestParam String search, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.getExerciseBySearch(search)));
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

    @GetMapping("/getMuscleName")
    public ResponseEntity<Object> getMuscleName(@RequestParam String muscleId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("muscleName", exerciseService.getMuscleName(muscleId)));
    }

    @GetMapping("/getEquipmentName")
    public ResponseEntity<Object> getEquipmentName(@RequestParam String equipmentId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("equipmentName", exerciseService.getEquipmentName(equipmentId)));
    }

    @GetMapping("/getTypeName")
    public ResponseEntity<Object> getTypeName(@RequestParam String typeId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("typeName", exerciseService.getTypeName(typeId)));
    }

    @GetMapping("/getExerciseCount")
    public ResponseEntity<Object> getExerciseCount(@RequestHeader("Authorization") String token, @RequestParam String group, @RequestParam String id) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        if (group.equals("muscle")) {
            return ResponseEntity.ok().body(Map.of("exerciseCount", exerciseService.getExerciseByMuscle(id).size()));
        } else if (group.equals("equipment")) {
            return ResponseEntity.ok().body(Map.of("exerciseCount", exerciseService.getExerciseByEquipment(id).size()));
        } else if (group.equals("type")) {
            return ResponseEntity.ok().body(Map.of("exerciseCount", exerciseService.getExerciseByType(id).size()));
        } else if (group.equals("search")) {
            return ResponseEntity.ok().body(Map.of("exerciseCount", exerciseService.getExerciseBySearch(id).size()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Invalid category"));
        }
    }

    @GetMapping("/getPaginatedExercisesByMuscle")
    public ResponseEntity<Object> getPaginatedExerciseByMuscle(@RequestParam String muscleId, @RequestParam int first, @RequestParam int size, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        List<ExerciseEntity> exercises = exerciseService.getExerciseByMuscle(muscleId);
        if (first >= exercises.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Invalid first value"));
        }
        if (first + size > exercises.size()) {
            return ResponseEntity.ok().body(Map.of("exerciseList", exercises.subList(first, exercises.size())));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exercises.subList(first, first + size)));
    }

    @GetMapping("/getPaginatedExercisesByEquipment")
    public ResponseEntity<Object> getPaginatedExerciseByEquipment(@RequestParam String equipmentId, @RequestParam int first, @RequestParam int size, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        List<ExerciseEntity> exercises = exerciseService.getExerciseByEquipment(equipmentId);
        if (first >= exercises.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Invalid first value"));
        }
        if (first + size > exercises.size()) {
            return ResponseEntity.ok().body(Map.of("exerciseList", exercises.subList(first, exercises.size())));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exercises.subList(first, first + size)));
    }

    @GetMapping("/getPaginatedExercisesByType")
    public ResponseEntity<Object> getPaginatedExerciseByType(@RequestParam String typeId, @RequestParam int first, @RequestParam int size, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        List<ExerciseEntity> exercises = exerciseService.getExerciseByType(typeId);
        if (first >= exercises.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Invalid first value"));
        }
        if (first + size > exercises.size()) {
            return ResponseEntity.ok().body(Map.of("exerciseList", exercises.subList(first, exercises.size())));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exercises.subList(first, first + size)));
    }

    @GetMapping("/getPaginatedExercisesBySearch")
    public ResponseEntity<Object> getPaginatedExerciseBySearch(@RequestParam String search, @RequestParam int first, @RequestParam int size, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        List<ExerciseEntity> exercises = exerciseService.getExerciseBySearch(search);
        if (first >= exercises.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Invalid first value"));
        }
        if (first + size > exercises.size()) {
            return ResponseEntity.ok().body(Map.of("exerciseList", exercises.subList(first, exercises.size())));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exercises.subList(first, first + size)));
    }

}

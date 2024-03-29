package com.capstone.app.controller;

import com.capstone.app.entity.ExerciseEntity;
import com.capstone.app.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.capstone.app.service.ExerciseServiceInterface;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    @Autowired
    private ExerciseServiceInterface exerciseService;

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
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.getExerciseByMuscle(muscleId, token)));
    }

    @GetMapping("/getExercisesByEquipment")
    public ResponseEntity<Object> getExerciseListByEquipment(@RequestParam String equipmentId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.getExerciseByEquipment(equipmentId, token)));
    }

    @GetMapping("/getExercisesByType")
    public ResponseEntity<Object> getExerciseListByType(@RequestParam String typeId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.getExerciseByType(typeId, token)));
    }

    @GetMapping("/getExercisesBySearch")
    public ResponseEntity<Object> getExerciseListBySearch(@RequestParam String search, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.getExerciseBySearch(search, token)));
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
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.filterExercises(exerciseName, token)));
    }

    @GetMapping("/filterMuscles")
    public ResponseEntity<Object> filterMuscles(@RequestParam String muscleName, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("muscleList", exerciseService.filterMuscles(muscleName)));
    }

    @GetMapping("/filterEquipments")
    public ResponseEntity<Object> filterEquipments(@RequestParam String equipmentName, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("equipmentList", exerciseService.filterEquipments(equipmentName)));
    }

    @GetMapping("/filterTypes")
    public ResponseEntity<Object> filterTypes(@RequestParam String typeName, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok().body(Map.of("typeList", exerciseService.filterTypes(typeName)));
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
            return ResponseEntity.ok().body(Map.of("exerciseCount", exerciseService.getExerciseByMuscle(id, token).size()));
        } else if (group.equals("equipment")) {
            return ResponseEntity.ok().body(Map.of("exerciseCount", exerciseService.getExerciseByEquipment(id, token).size()));
        } else if (group.equals("type")) {
            return ResponseEntity.ok().body(Map.of("exerciseCount", exerciseService.getExerciseByType(id, token).size()));
        } else if (group.equals("search")) {
            return ResponseEntity.ok().body(Map.of("exerciseCount", exerciseService.getExerciseBySearch(id, token).size()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Invalid category"));
        }
    }

    @GetMapping("/getPaginatedExercisesByMuscle")
    public ResponseEntity<Object> getPaginatedExerciseByMuscle(@RequestParam String muscleId, @RequestParam int first, @RequestParam int size, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        List<ExerciseEntity> exercises = exerciseService.getExerciseByMuscle(muscleId, token);
        if (first >= exercises.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "No Results Found"));
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
        List<ExerciseEntity> exercises = exerciseService.getExerciseByEquipment(equipmentId, token);
        if (first >= exercises.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "No Results Found"));
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
        List<ExerciseEntity> exercises = exerciseService.getExerciseByType(typeId, token);
        if (first >= exercises.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "No Results Found"));
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
        List<ExerciseEntity> exercises = exerciseService.getExerciseBySearch(search, token);
        if (first >= exercises.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "No Results Found"));
        }
        if (first + size > exercises.size()) {
            return ResponseEntity.ok().body(Map.of("exerciseList", exercises.subList(first, exercises.size())));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exercises.subList(first, first + size)));
    }

    @PostMapping("/saveExercise")
    public ResponseEntity<Object> saveExercise(@RequestBody ExerciseEntity exercise, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        if (exercise.getMinorMuscles().stream().anyMatch(muscle -> muscle.getMuscleId() == exercise.getMajorMuscle().getMuscleId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "You cannot select the same muscle as major and minor muscle. Please select a different muscle."));
        }
        exerciseService.saveExercise(exercise, token);
        return ResponseEntity.ok().body(Map.of("message", "Exercise saved successfully"));
    }

    @DeleteMapping("/deleteExercise")
    public ResponseEntity<Object> deleteExercise(@RequestParam int exerciseId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        exerciseService.deleteExercise(exerciseId);
        return ResponseEntity.ok().body(Map.of("message", "Exercise deleted successfully"));
    }

    @GetMapping("/getExerciseById")
    public ResponseEntity<Object> getExerciseById(@RequestParam int exerciseId, @RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        ExerciseEntity exercise = exerciseService.getExerciseById(exerciseId);
        if (exercise == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Exercise not found"));
        }
        return ResponseEntity.ok().body(Map.of("exercise", exercise));
    }

}

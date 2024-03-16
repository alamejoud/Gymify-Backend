package com.capstone.app.controller;

import com.capstone.app.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import com.capstone.app.service.ExerciseServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getExerciseMuscles")
    public ResponseEntity<Object> getExerciseTypes(@RequestParam String token) {
        if (jwtService.extractExpiration(token).before(new Date())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "Token has expired"));
        }
        return ResponseEntity.ok().body(Map.of("exerciseMuscles", exerciseService.getExerciseMuscles()));
    }

    @GetMapping("/getExerciseList")
    public ResponseEntity<Object> getExerciseList(@RequestParam String muscleId, @RequestParam String token) {
        if (jwtService.extractExpiration(token).before(new Date())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "Token has expired"));
        }
        return ResponseEntity.ok().body(Map.of("exerciseList", exerciseService.getExerciseList(muscleId)));
    }
}

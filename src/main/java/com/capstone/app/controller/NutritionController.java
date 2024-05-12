package com.capstone.app.controller;

import com.capstone.app.entity.DietEntity;
import com.capstone.app.service.NutritionServiceInterface;
import com.capstone.app.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/nutrition")
public class NutritionController {

    @Autowired
    private NutritionServiceInterface nutritionService;

    @GetMapping("/getRecipeById")
    public ResponseEntity<Object> getRecipeById(@RequestHeader("Authorization") String token, @RequestParam int recipeId) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok(Map.of("recipe", nutritionService.getRecipeById(recipeId)));
    }

    @GetMapping("/filterRecipes")
    public ResponseEntity<Object> filterRecipes(@RequestHeader("Authorization") String token, @RequestParam String search, @RequestParam int first, @RequestParam int rows, @RequestParam String searchOption) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("recipeList", nutritionService.filterRecipes(search, first, rows, searchOption));
        response.put("totalRecords", nutritionService.filterRecipes(search, 0, Integer.MAX_VALUE, searchOption).size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getDietPlans")
    public ResponseEntity<Object> getDietPlans(@RequestHeader("Authorization") String token, @RequestParam("onlyCreatedDietPlans") boolean onlyCreatedDietPlans) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("dietPlanList", nutritionService.getDietPlans(token, onlyCreatedDietPlans));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveDiet")
    public ResponseEntity<Object> saveDiet(@RequestHeader("Authorization") String token, @RequestBody DietEntity diet) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        nutritionService.saveDiet(token, diet);
        return ResponseEntity.ok(Map.of("message", "Diet saved successfully."));
    }

    @DeleteMapping("/deleteDiet")
    public ResponseEntity<Object> deleteDiet(@RequestHeader("Authorization") String token, @RequestParam int dietId) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        nutritionService.deleteDiet(dietId);
        return ResponseEntity.ok(Map.of("message", "Diet deleted successfully."));
    }

    @PostMapping("/subscribeToDiet")
    public ResponseEntity<Object> subscribeToDiet(@RequestHeader("Authorization") String token, @RequestParam int dietId) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        nutritionService.subscribeToDiet(token, dietId);
        return ResponseEntity.ok(Map.of("message", "Subscribed to diet successfully."));
    }

    @DeleteMapping("/unsubscribeToDiet")
    public ResponseEntity<Object> unsubscribeToDiet(@RequestHeader("Authorization") String token, @RequestParam int dietId) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        nutritionService.unsubscribeToDiet(token, dietId);
        return ResponseEntity.ok(Map.of("message", "Unsubscribed from diet successfully."));
    }

    @GetMapping("/getDietitiansDietPlans")
    public ResponseEntity<Object> getDietitiansDietPlans(@RequestHeader("Authorization") String token, @RequestParam String search) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok(Map.of("dietPlanList", nutritionService.getDietitiansDietPlans(search)));
    }

    @GetMapping("/getMyDietNames")
    public ResponseEntity<Object> getMyDietNames(@RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok(Map.of("dietNames", nutritionService.getMyDietNames(token)));
    }

}

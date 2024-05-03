package com.capstone.app.controller;

import com.capstone.app.entity.RecipeEntity;
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

}

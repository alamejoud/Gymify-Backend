package com.capstone.app.service;

import com.capstone.app.entity.RecipeEntity;
import com.capstone.app.repository.NutritionRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionService implements NutritionServiceInterface{

    @Autowired
    private NutritionRepositoryInterface nutritionRepository;

    @Override
    public RecipeEntity getRecipeById(int recipeId) {
        return nutritionRepository.getRecipeById(recipeId);
    }

    @Override
    public List<RecipeEntity> filterRecipes(String search, int first, int rows, String searchOption) {
        return nutritionRepository.filterRecipes(search, first, rows, searchOption);
    }
}

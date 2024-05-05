package com.capstone.app.service;

import com.capstone.app.entity.DietEntity;
import com.capstone.app.entity.RecipeEntity;

import java.util.List;

public interface NutritionServiceInterface {

    public RecipeEntity getRecipeById(int recipeId);
    public List<RecipeEntity> filterRecipes(String search, int first, int rows, String searchOption);
    public List<DietEntity> getDietPlans(String token, boolean onlyCreatedDietPlans);
    public void saveDiet(String token, DietEntity diet);
    public void deleteDiet(int dietId);
    public void subscribeToDiet(String token, int dietId);
    public void unsubscribeToDiet(String token, int dietId);
    public List<DietEntity> getDietitiansDietPlans(String search);
}

package com.capstone.app.repository;

import com.capstone.app.entity.DietEntity;
import com.capstone.app.entity.RecipeEntity;

import java.util.List;

public interface NutritionRepositoryInterface {

    public RecipeEntity getRecipeById(int recipeId);
    public List<RecipeEntity> filterRecipes(String search, int first, int rows, String searchOption);
    public List<DietEntity> getDietPlans(String username, boolean onlyCreatedDietPlans);
    public void saveDiet(DietEntity diet);
    public void deleteDiet(int dietId);
    public void subscribeToDiet(int dietId, int userId);
    public void unsubscribeToDiet(int dietId, int userId);
    public List<DietEntity> getDietitiansDietPlans(String search);
}

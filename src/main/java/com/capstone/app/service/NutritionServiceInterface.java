package com.capstone.app.service;

import com.capstone.app.entity.RecipeEntity;

import java.util.List;

public interface NutritionServiceInterface {

    public RecipeEntity getRecipeById(int recipeId);
    public List<RecipeEntity> filterRecipes(String search, int first, int rows, String searchOption);
}

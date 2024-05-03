package com.capstone.app.repository;

import com.capstone.app.entity.RecipeEntity;

import java.util.List;

public interface NutritionRepositoryInterface {

    public RecipeEntity getRecipeById(int recipeId);
    public List<RecipeEntity> filterRecipes(String search, int first, int rows, String searchOption);
}

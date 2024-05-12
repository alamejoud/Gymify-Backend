package com.capstone.app.service;

import com.capstone.app.entity.DietEntity;
import com.capstone.app.entity.DietRecipeEntity;
import com.capstone.app.entity.RecipeEntity;
import com.capstone.app.repository.NutritionRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NutritionService implements NutritionServiceInterface{

    @Autowired
    private NutritionRepositoryInterface nutritionRepository;
    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private JwtService jwtService;

    @Override
    public RecipeEntity getRecipeById(int recipeId) {
        return nutritionRepository.getRecipeById(recipeId);
    }

    @Override
    public List<RecipeEntity> filterRecipes(String search, int first, int rows, String searchOption) {
        return nutritionRepository.filterRecipes(search, first, rows, searchOption);
    }

    @Override
    public List<DietEntity> getDietPlans(String token, boolean onlyCreatedDietPlans) {
        return nutritionRepository.getDietPlans(jwtService.extractUsername(token), onlyCreatedDietPlans);
    }

    @Override
    public void saveDiet(String token, DietEntity diet) {
        diet.setCreatedBy(userService.getUserByUsername(jwtService.extractUsername(token)));
        if (diet.getUsers().stream().noneMatch(user -> user.getUsername().toLowerCase().equals(jwtService.extractUsername(token).toLowerCase()))) {
            diet.getUsers().add(userService.getUserByUsername(jwtService.extractUsername(token)));
        }
        diet.getUsers().forEach(user -> {
            if (user.getDiets().stream().noneMatch(dietEntity -> dietEntity.getDietId() == diet.getDietId())) {
                user.getDiets().add(diet);
            }
        });
        int recipeOrder = 1;
        for (DietRecipeEntity dietRecipeEntity : diet.getMondayRecipes()) {
            dietRecipeEntity.setRecipeOrder(recipeOrder++);
            dietRecipeEntity.setDiet(diet);
            dietRecipeEntity.getRecipe().getDietRecipes().add(dietRecipeEntity);
        }
        recipeOrder = 1;
        for (DietRecipeEntity dietRecipeEntity : diet.getTuesdayRecipes()) {
            dietRecipeEntity.setRecipeOrder(recipeOrder++);
            dietRecipeEntity.setDiet(diet);
            dietRecipeEntity.getRecipe().getDietRecipes().add(dietRecipeEntity);
        }
        recipeOrder = 1;
        for (DietRecipeEntity dietRecipeEntity : diet.getWednesdayRecipes()) {
            dietRecipeEntity.setRecipeOrder(recipeOrder++);
            dietRecipeEntity.setDiet(diet);
            dietRecipeEntity.getRecipe().getDietRecipes().add(dietRecipeEntity);
        }
        recipeOrder = 1;
        for (DietRecipeEntity dietRecipeEntity : diet.getThursdayRecipes()) {
            dietRecipeEntity.setRecipeOrder(recipeOrder++);
            dietRecipeEntity.setDiet(diet);
            dietRecipeEntity.getRecipe().getDietRecipes().add(dietRecipeEntity);
        }
        recipeOrder = 1;
        for (DietRecipeEntity dietRecipeEntity : diet.getFridayRecipes()) {
            dietRecipeEntity.setRecipeOrder(recipeOrder++);
            dietRecipeEntity.setDiet(diet);
            dietRecipeEntity.getRecipe().getDietRecipes().add(dietRecipeEntity);
        }
        recipeOrder = 1;
        for (DietRecipeEntity dietRecipeEntity : diet.getSaturdayRecipes()) {
            dietRecipeEntity.setRecipeOrder(recipeOrder++);
            dietRecipeEntity.setDiet(diet);
            dietRecipeEntity.getRecipe().getDietRecipes().add(dietRecipeEntity);
        }
        recipeOrder = 1;
        for (DietRecipeEntity dietRecipeEntity : diet.getSundayRecipes()) {
            dietRecipeEntity.setRecipeOrder(recipeOrder++);
            dietRecipeEntity.setDiet(diet);
            dietRecipeEntity.getRecipe().getDietRecipes().add(dietRecipeEntity);
        }
        nutritionRepository.saveDiet(diet);
    }

    @Override
    public void deleteDiet(int dietId) {
        nutritionRepository.deleteDiet(dietId);
    }

    @Override
    public void subscribeToDiet(String token, int dietId) {
        nutritionRepository.subscribeToDiet(dietId, userService.getUserByUsername(jwtService.extractUsername(token)).getUserId());
    }

    @Override
    public void unsubscribeToDiet(String token, int dietId) {
        nutritionRepository.unsubscribeToDiet(dietId, userService.getUserByUsername(jwtService.extractUsername(token)).getUserId());
    }

    @Override
    public List<DietEntity> getDietitiansDietPlans(String search) {
        return nutritionRepository.getDietitiansDietPlans(search);
    }

    @Override
    public List<DietEntity> getMyDietNames(String token) {
        List<DietEntity> diets = nutritionRepository.getDietPlans(jwtService.extractUsername(token), false);
        List<DietEntity> dietsToSend = new ArrayList<>();
        diets.forEach(diet -> {
            DietEntity dietToSend = new DietEntity();
            dietToSend.setDietId(diet.getDietId());
            dietToSend.setDietName(diet.getDietName());
            dietsToSend.add(dietToSend);
        });
        return dietsToSend.size() > 4 ? dietsToSend.subList(0, 4) : dietsToSend;
    }
}

package com.capstone.app.repository;

import com.capstone.app.entity.RecipeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class NutritionRepository implements NutritionRepositoryInterface{

    @Autowired
    private EntityManager entityManager;
    @Override
    public RecipeEntity getRecipeById(int recipeId) {
        return entityManager.find(RecipeEntity.class, recipeId);
    }

    @Override
    public List<RecipeEntity> filterRecipes(String search, int first, int rows, String searchOption) {
        List<RecipeEntity> recipes = new ArrayList<>();
        String[] searches = search.split("\\s+");
        if (search.trim().equals("")) {
            recipes = entityManager.createQuery("SELECT r FROM RecipeEntity r", RecipeEntity.class)
                    .getResultList();
        } else if (searchOption.equals("everything")) {
            String query = "Select r from RecipeEntity r " +
                    "join r.recipeCategory c " +
                    "left join r.keywords k " +
                    "left join r.ingredients ri " +
                    "left join ri.ingredient i " +
                    "where lower(r.recipeName) like lower(:search) " +
                    "or lower(r.description) like lower(:search) " +
                    "or lower(c.recipeCategory) like lower(:search) " +
                    "or lower(k.keyword) like lower(:search) " +
                    "or lower(i.ingredientName) like lower(:search) ";
            recipes = entityManager.createQuery(query, RecipeEntity.class)
                    .setParameter("search", "%" + search + "%")
                    .getResultList();
        } else if (searchOption.equals("name")) {
            String query = "Select r from RecipeEntity r where ";
            for (int i = 0; i < searches.length; i++) {
                query += " lower(r.recipeName) like lower(:search" + i + ")";
                if (i != searches.length - 1) {
                    query += " or ";
                }
            }
            TypedQuery<RecipeEntity> recipeEntityTypedQuery = entityManager.createQuery(query, RecipeEntity.class);
            for (int i = 0; i < searches.length; i++) {
                recipeEntityTypedQuery.setParameter("search" + i, "%" + searches[i] + "%");
            }
            recipes = recipeEntityTypedQuery.getResultList();

        } else if (searchOption.equals("category")) {
            String query = "Select r from RecipeEntity r join r.recipeCategory c where ";
            for (int i = 0; i < searches.length; i++) {
                query += " lower(c.recipeCategory) like lower(:search" + i + ")";
                if (i != searches.length - 1) {
                    query += " or ";
                }
            }
            TypedQuery<RecipeEntity> recipeEntityTypedQuery = entityManager.createQuery(query, RecipeEntity.class);
            for (int i = 0; i < searches.length; i++) {
                recipeEntityTypedQuery.setParameter("search" + i, "%" + searches[i] + "%");
            }
            recipes = recipeEntityTypedQuery.getResultList();

        } else if (searchOption.equals("ingredients")) {
            String query = "Select r from RecipeEntity r join r.ingredients ri join ri.ingredient i where ";
            for (int i = 0; i < searches.length; i++) {
                query += " lower(i.ingredientName) like lower(:search" + i + ")";
                if (i != searches.length - 1) {
                    query += " or ";
                }
            }
            TypedQuery<RecipeEntity> recipeEntityTypedQuery = entityManager.createQuery(query, RecipeEntity.class);
            for (int i = 0; i < searches.length; i++) {
                recipeEntityTypedQuery.setParameter("search" + i, "%" + searches[i] + "%");
            }
            recipes = recipeEntityTypedQuery.getResultList();

        }
        recipes.sort(Comparator.comparingInt(r -> -r.getImages().size()));
        if (first + rows > recipes.size()) {
            return recipes.subList(first, recipes.size());
        }
        return recipes.subList(first, first + rows);
    }

}

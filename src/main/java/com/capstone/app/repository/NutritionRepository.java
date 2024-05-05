package com.capstone.app.repository;

import com.capstone.app.entity.DietEntity;
import com.capstone.app.entity.RecipeEntity;
import com.capstone.app.entity.WorkoutEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
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
                    .setParameter("search", "%" + search.trim() + "%")
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
                recipeEntityTypedQuery.setParameter("search" + i, "%" + searches[i].trim() + "%");
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
                recipeEntityTypedQuery.setParameter("search" + i, "%" + searches[i].trim() + "%");
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
                recipeEntityTypedQuery.setParameter("search" + i, "%" + searches[i].trim() + "%");
            }
            recipes = recipeEntityTypedQuery.getResultList();

        }
        recipes.sort(Comparator.comparingInt(r -> -r.getImages().size()));
        if (first + rows > recipes.size()) {
            return recipes.subList(first, recipes.size());
        }
        return recipes.subList(first, first + rows);
    }

    @Override
    public List<DietEntity> getDietPlans(String username, boolean onlyCreatedDietPlans) {
        List<DietEntity> diets = new ArrayList<>();
        if (onlyCreatedDietPlans) {
            diets = entityManager.createQuery("SELECT w FROM DietEntity w join w.createdBy c WHERE lower(c.username) = lower(:username) order by dietId DESC", DietEntity.class)
                    .setParameter("username", username).getResultList();
        } else {
            diets = entityManager.createQuery("SELECT w FROM DietEntity w join w.users as u WHERE lower(u.username) = lower(:username) order by dietId DESC", DietEntity.class)
                    .setParameter("username", username)
                    .getResultList();
        }
        return diets;
    }

    @Override
    public void saveDiet(DietEntity diet) {
        Query query = entityManager.createQuery("DELETE FROM DietRecipeEntity d WHERE d.dietId = :dietId");
        query.setParameter("dietId", diet.getDietId());
        query.executeUpdate();
        entityManager.merge(diet);
    }

    @Override
    public void deleteDiet(int dietId) {
        Query query = entityManager.createQuery("DELETE FROM DietRecipeEntity d WHERE d.dietId = :dietId");
        query.setParameter("dietId", dietId);
        query.executeUpdate();
        DietEntity diet = entityManager.find(DietEntity.class, dietId);
        entityManager.remove(diet);
    }

    @Override
    public void subscribeToDiet(int dietId, int userId) {
        Query query = entityManager.createNativeQuery("INSERT INTO G_USER_DIETS VALUES (?1, ?2)");
        query.setParameter(1, userId);
        query.setParameter(2, dietId);
        query.executeUpdate();
    }

    @Override
    public void unsubscribeToDiet(int dietId, int userId) {
        Query query = entityManager.createNativeQuery("DELETE FROM G_USER_DIETS WHERE user_id = ?1 and diet_id = ?2");
        query.setParameter(1, userId);
        query.setParameter(2, dietId);
        query.executeUpdate();
    }

    @Override
    public List<DietEntity> getDietitiansDietPlans(String search) {
        List<DietEntity> diets = entityManager.createQuery("SELECT d FROM DietEntity d join d.createdBy c where lower(d.dietName) like lower(:search) and (c.role = 'dietitian' or c.role = 'admin') order by dietId DESC", DietEntity.class)
                .setParameter("search", "%" + search.trim() + "%").getResultList();
        return diets;
    }

}

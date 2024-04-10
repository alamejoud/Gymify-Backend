package com.capstone.app.repository;

import com.capstone.app.entity.WorkoutEntity;
import com.capstone.app.entity.WorkoutExerciseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class WorkoutRepository implements WorkoutRepositoryInterface{

    @Autowired
    private EntityManager entityManager;

    @Override
    public WorkoutEntity getWorkoutById(int id) {
        WorkoutEntity workout = entityManager.find(WorkoutEntity.class, id);
        return workout;
    }

    @Override
    public void saveWorkout(WorkoutEntity workout) {
        Query query = entityManager.createQuery("DELETE FROM WorkoutExerciseEntity w WHERE w.workoutId = :workoutId");
        query.setParameter("workoutId", workout.getWorkoutId());
        query.executeUpdate();
        entityManager.merge(workout);
    }

    @Override
    public List<WorkoutEntity> getMyWorkouts(String username, boolean onlyCreatedWorkout) {
        List<WorkoutEntity> workouts = new ArrayList<>();
        if (onlyCreatedWorkout) {
            workouts = entityManager.createQuery("SELECT w FROM WorkoutEntity w join w.createdBy c WHERE lower(c.username) = lower(:username)", WorkoutEntity.class)
                    .setParameter("username", username).getResultList();
        } else {
            workouts = entityManager.createQuery("SELECT w FROM WorkoutEntity w join w.users as u WHERE lower(u.username) = lower(:username)", WorkoutEntity.class)
                    .setParameter("username", username)
                    .getResultList();
        }
        return workouts;
    }

    @Override
    public void deleteWorkout(int workoutId) {
        Query query = entityManager.createQuery("DELETE FROM WorkoutExerciseEntity w WHERE w.workoutId = :workoutId");
        query.setParameter("workoutId", workoutId);
        query.executeUpdate();
        WorkoutEntity workout = entityManager.find(WorkoutEntity.class, workoutId);
        entityManager.remove(workout);
    }

    @Override
    public List<WorkoutEntity> getTrainersWorkouts(String search) {
        List<WorkoutEntity> workouts = entityManager.createQuery("SELECT w FROM WorkoutEntity w join w.createdBy c where lower(w.workoutName) like lower(:search) and (c.role = 'trainer' or c.role = 'admin')", WorkoutEntity.class)
                .setParameter("search", "%" + search + "%").getResultList();
        return workouts;
    }

    @Override
    public void subscribeToWorkout(int workoutId, int userId) {
        Query query = entityManager.createNativeQuery("INSERT INTO G_USER_WORKOUTS VALUES (?1, ?2)");
        query.setParameter(1, userId);
        query.setParameter(2, workoutId);
        query.executeUpdate();
    }

    @Override
    public void unsubscribeFromWorkout(int workoutId, int userId) {
        Query query = entityManager.createNativeQuery("DELETE FROM G_USER_WORKOUTS WHERE user_id = ?1 and workout_id = ?2");
        query.setParameter(1, userId);
        query.setParameter(2, workoutId);
        query.executeUpdate();
    }
}

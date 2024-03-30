package com.capstone.app.repository;

import com.capstone.app.entity.WorkoutEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}

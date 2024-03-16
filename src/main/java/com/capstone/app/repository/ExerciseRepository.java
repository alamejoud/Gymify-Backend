package com.capstone.app.repository;

import com.capstone.app.entity.ExerciseEntity;
import com.capstone.app.entity.MuscleEntity;
import com.capstone.app.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ExerciseRepository implements ExerciseRepositoryInterface{

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<MuscleEntity> getExerciseMuscles(){
        List<MuscleEntity> muscles = entityManager.createQuery("SELECT m FROM MuscleEntity m", MuscleEntity.class).getResultList();
        return muscles;
    }

    @Override
    public List<ExerciseEntity> getExerciseList(String muscleId) {
        String query = "SELECT e.exerciseName FROM ExerciseEntity e join e.majorMuscle m WHERE m.muscleId = :majorMuscleId union all SELECT e.exerciseName FROM ExerciseEntity e inner join e.minorMuscles m where m.muscleId = :minorMuscleId";
        List<ExerciseEntity> exercises = entityManager.createQuery(query, ExerciseEntity.class).setParameter("majorMuscleId", muscleId).setParameter("minorMuscleId", muscleId).getResultList();
        return exercises;
    }

}

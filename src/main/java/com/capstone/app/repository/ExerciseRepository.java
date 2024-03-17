package com.capstone.app.repository;

import com.capstone.app.entity.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class ExerciseRepository implements ExerciseRepositoryInterface{

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<MuscleEntity> getExerciseMuscles() {
        List<MuscleEntity> muscles = entityManager.createQuery("SELECT m FROM MuscleEntity m", MuscleEntity.class).getResultList();
        return muscles;
    }

    @Override
    public List<EquipmentEntity> getExerciseEquipments() {
        List<EquipmentEntity> equipments = entityManager.createQuery("SELECT e FROM EquipmentEntity e", EquipmentEntity.class).getResultList();
        return equipments;
    }

    @Override
    public List<TypeEntity> getExerciseTypes() {
        List<TypeEntity> types = entityManager.createQuery("SELECT t FROM TypeEntity t", TypeEntity.class).getResultList();
        return types;
    }

    @Override
    public List<ExerciseEntity> getExerciseList(String muscleId) {
        String query = "SELECT e FROM ExerciseEntity e join e.majorMuscle m where m.muscleId = :majorMuscleId union all select e from ExerciseEntity e join e.minorMuscles m where m.muscleId = :minorMuscleId";
        List<ExerciseEntity> exercises = entityManager.createQuery(query, ExerciseEntity.class).setParameter("majorMuscleId", muscleId).setParameter("minorMuscleId", muscleId).getResultList();
        return exercises;
    }

    @Override
    public List<LookupEntity> getExerciseGroups() {
        List<LookupEntity> groups = entityManager.createQuery("SELECT l FROM LookupEntity l where l.lookupType = 'EXERCISE_GROUP'", LookupEntity.class).getResultList();
        return groups;
    }

    @Override
    public List<ExerciseEntity> filterExercises(String exerciseName) {
        if (exerciseName == null || exerciseName.trim().isEmpty()) {
            return Collections.emptyList();
        }
//        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//        JPAQuery<?> query = queryFactory.selectFrom(QExerciseEntity.exerciseEntity).where(QExerciseEntity.exerciseEntity.exerciseName.contains(exerciseName));
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExerciseEntity> cq = qb.createQuery(ExerciseEntity.class);

        Root<ExerciseEntity> root = cq.from(ExerciseEntity.class);
        cq.where( qb.or(
                qb.like(root.get("exerciseName"), qb.parameter(String.class, "exerciseName"))
        ));
        Query query = entityManager.createQuery(cq);
        query.setParameter("exerciseName", "%" + exerciseName + "%");
        List<ExerciseEntity> exercises = query.getResultList().subList(0, 5);
        return exercises;
    }

}

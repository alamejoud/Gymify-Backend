package com.capstone.app.repository;

import com.capstone.app.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<ExerciseEntity> getExerciseByMuscle(String muscleId, String username, String role) {
        String query = "SELECT e FROM ExerciseEntity e join e.majorMuscle m where m.muscleId = :majorMuscleId union all select e from ExerciseEntity e join e.minorMuscles m where m.muscleId = :minorMuscleId";
        List<ExerciseEntity> exercises = entityManager.createQuery(query, ExerciseEntity.class).setParameter("majorMuscleId", muscleId).setParameter("minorMuscleId", muscleId).getResultList();
        return exercises;
    }

    @Override
    public List<ExerciseEntity> getExerciseByEquipment(String equipmentId, String username, String role) {
        List<ExerciseEntity> exercises = entityManager.createQuery("SELECT e FROM ExerciseEntity e join e.equipments ee where ee.equipmentId = :equipmentId", ExerciseEntity.class).setParameter("equipmentId", equipmentId).getResultList();
        return exercises;
    }

    @Override
    public List<ExerciseEntity> getExerciseByType(String typeId, String username, String role) {
        List<ExerciseEntity> exercises = entityManager.createQuery("SELECT e FROM ExerciseEntity e join e.types t where t.typeId = :typeId", ExerciseEntity.class).setParameter("typeId", typeId).getResultList();
        return exercises;
    }

    @Override
    public List<ExerciseEntity> getExerciseBySearch(String search, String username, String role) {
        List<ExerciseEntity> exercises = entityManager.createQuery("SELECT e FROM ExerciseEntity e where lower(e.exerciseName) like lower(:search)", ExerciseEntity.class).setParameter("search", "%" + search + "%").getResultList();
        return exercises;
    }

    @Override
    public List<LookupEntity> getExerciseGroups() {
        List<LookupEntity> groups = entityManager.createQuery("SELECT l FROM LookupEntity l where l.lookupType = 'EXERCISE_GROUP'", LookupEntity.class).getResultList();
        return groups;
    }

    @Override
    public List<ExerciseEntity> filterExercises(String exerciseName, String username, String role) {
        List<ExerciseEntity> exercises = getExerciseBySearch(exerciseName, username, role);
        if (exercises.size() < 4) {
            return exercises;
        } else {
            return exercises.subList(0, 4);
        }
    }

    @Override
    public List<MuscleEntity> filterMuscles(String muscleName) {
        List<MuscleEntity> muscles = entityManager.createQuery("SELECT m FROM MuscleEntity m where lower(m.muscleName) like lower(:muscleName)", MuscleEntity.class).setParameter("muscleName", "%" + muscleName.toLowerCase() + "%").getResultList();
        return muscles;
    }

    @Override
    public List<EquipmentEntity> filterEquipments(String equipmentName) {
        List<EquipmentEntity> equipments = entityManager.createQuery("SELECT e FROM EquipmentEntity e where  lower(e.equipmentName) like lower(:equipmentName)", EquipmentEntity.class).setParameter("equipmentName", "%" + equipmentName.toLowerCase() + "%").getResultList();
        return equipments;
    }

    @Override
    public List<TypeEntity> filterTypes(String typeName) {
        List<TypeEntity> types = entityManager.createQuery("SELECT t FROM TypeEntity t where  lower(t.typeName) like lower(:typeName)", TypeEntity.class).setParameter("typeName", "%" + typeName.toLowerCase() + "%").getResultList();
        return types;
    }

    @Override
    public String getMuscleName(String muscleId) {
        MuscleEntity muscle = entityManager.find(MuscleEntity.class, muscleId);
        return muscle.getMuscleName();
    }

    @Override
    public String getEquipmentName(String equipmentId) {
        EquipmentEntity equipment = entityManager.find(EquipmentEntity.class, equipmentId);
        return equipment.getEquipmentName();
    }

    @Override
    public String getTypeName(String typeId) {
        TypeEntity type = entityManager.find(TypeEntity.class, typeId);
        return type.getTypeName();
    }

    @Override
    public EquipmentEntity getEquipmentById(int equipmentId) {
        EquipmentEntity equipment = entityManager.find(EquipmentEntity.class, equipmentId);
        return equipment;
    }

    @Override
    public void saveExercise(ExerciseEntity exercise) {
        entityManager.merge(exercise);
    }

    @Override
    public void deleteExercise(int exerciseId) {
        ExerciseEntity exercise = entityManager.find(ExerciseEntity.class, exerciseId);
        entityManager.remove(exercise);
    }

    @Override
    public ExerciseEntity getExerciseById(int equipmentId) {
        ExerciseEntity exercise = entityManager.find(ExerciseEntity.class, equipmentId);
        return exercise;
    }

    @Override
    public List<ExerciseEntity> getExerciseList() {
        List<ExerciseEntity> exercises = entityManager.createQuery("SELECT e FROM ExerciseEntity e", ExerciseEntity.class).getResultList();
        return exercises;
    }

}

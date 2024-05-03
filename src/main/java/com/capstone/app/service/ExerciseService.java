package com.capstone.app.service;

import com.capstone.app.entity.*;
import com.capstone.app.repository.ExerciseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService implements ExerciseServiceInterface{
    @Autowired
    private ExerciseRepositoryInterface exerciseRepository;
    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private JwtService jwtService;
    @Override
    public List<MuscleEntity> getExerciseMuscles() {
            return exerciseRepository.getExerciseMuscles();
    }

    @Override
    public List<EquipmentEntity> getExerciseEquipments() {
        return exerciseRepository.getExerciseEquipments();
    }

    @Override
    public List<TypeEntity> getExerciseTypes() {
        return exerciseRepository.getExerciseTypes();
    }

    @Override
    public List<ExerciseEntity> getExerciseByMuscle(String muscleId, String token) {
        return exerciseRepository.getExerciseByMuscle(muscleId, jwtService.extractUsername(token), jwtService.extractRole(token));
    }

    @Override
    public List<ExerciseEntity> getExerciseByEquipment(String equipmentId, String token) {
        return exerciseRepository.getExerciseByEquipment(equipmentId, jwtService.extractUsername(token), jwtService.extractRole(token));
    }

    @Override
    public List<ExerciseEntity> getExerciseByType(String typeId, String token) {
        return exerciseRepository.getExerciseByType(typeId, jwtService.extractUsername(token), jwtService.extractRole(token));
    }

    @Override
    public List<ExerciseEntity> getExerciseBySearch(String search, String token) {
        return exerciseRepository.getExerciseBySearch(search, jwtService.extractUsername(token), jwtService.extractRole(token));
    }

    @Override
    public List<LookupEntity> getExerciseGroups() {
        return exerciseRepository.getExerciseGroups();
    }

    @Override
    public List<ExerciseEntity> filterExercises(String exerciseName, String token) {
        return exerciseRepository.filterExercises(exerciseName, jwtService.extractUsername(token), jwtService.extractRole(token));
    }

    @Override
    public List<MuscleEntity> filterMuscles(String muscleName) {
        return exerciseRepository.filterMuscles(muscleName);
    }

    @Override
    public List<EquipmentEntity> filterEquipments(String equipmentName) {
        return exerciseRepository.filterEquipments(equipmentName);
    }

    @Override
    public List<TypeEntity> filterTypes(String typeName) {
        return exerciseRepository.filterTypes(typeName);
    }

    @Override
    public String getMuscleName(String muscleId) {
       return exerciseRepository.getMuscleName(muscleId);
    }

    @Override
    public String getEquipmentName(String equipmentId) {
        return exerciseRepository.getEquipmentName(equipmentId);
    }

    @Override
    public String getTypeName(String typeId) {
        return exerciseRepository.getTypeName(typeId);
    }

    @Override
    public void saveExercise(ExerciseEntity exercise, String token) {
        exercise.setCreatedBy(userService.getUserByUsername(jwtService.extractUsername(token)));
        exercise.setVideoLink("https://www.youtube.com/embed/"+exercise.getVideoLink());

        exerciseRepository.saveExercise(exercise);
    }

    @Override
    public void deleteExercise(int exerciseId) {
        exerciseRepository.deleteExercise(exerciseId);
    }

    @Override
    public ExerciseEntity getExerciseById(int equipmentId) {
        return exerciseRepository.getExerciseById(equipmentId);
    }

    @Override
    public List<ExerciseEntity> getExerciseList() {
        return exerciseRepository.getExerciseList();
    }

}

package com.capstone.app.repository;

import com.capstone.app.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserRepository implements UserRepositoryInterface{

    @Autowired
    private EntityManager entityManager;
    public UserRepository() {

    }

    @Override
    public void addUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public void updateUser(int id, String name) {

    }

    @Override
    public UserEntity getUser(int id) {
        return null;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return null;
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        List<UserEntity> results = entityManager.createQuery("SELECT u FROM UserEntity u WHERE LOWER(u.username) = LOWER(:username)", UserEntity.class)
                .setParameter("username", username).getResultList();
        if (results == null || results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public List<UserEntity> getUsers(String username, String role) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE LOWER(u.username) NOT LIKE LOWER(:username) AND LOWER(u.role) LIKE LOWER(:role)", UserEntity.class)
                .setParameter("username", "%" + username + "%")
                .setParameter("role", "%" + role + "%")
                .getResultList();
    }
}

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
        return entityManager.createQuery("FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}

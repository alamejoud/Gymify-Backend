package com.capstone.app.repository;


import com.capstone.app.entity.UserEntity;

import java.util.List;

public interface UserRepositoryInterface {

    public void addUser(UserEntity userEntity);
    public void deleteUser(int id);
    public void updateUser(int id, String name);
    public UserEntity getUser(int id);
    public List<UserEntity> getAllUsers();
}

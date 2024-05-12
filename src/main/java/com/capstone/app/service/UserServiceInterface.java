package com.capstone.app.service;

import com.capstone.app.exception.UserAlreadyExistsException;
import com.capstone.app.entity.UserEntity;

import java.util.List;

public interface UserServiceInterface {

        public void addUser(UserEntity userEntity) throws UserAlreadyExistsException;
        public void deleteUser(int id);
        public void updateUser(UserEntity user);
        public UserEntity getUser(int id);
        public UserEntity getUserByUsername(String username);
        public List<UserEntity> getAllUsers();
        public boolean checkUserExists(String username);
        public boolean checkUser(String username, String password);
        public UserEntity getUserByUsernameAndPassword(String username, String password);
        public List<UserEntity> getUsers(String username, String role);
}

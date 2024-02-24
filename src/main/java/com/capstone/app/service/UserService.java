package com.capstone.app.service;

import com.capstone.app.entity.UserEntity;
import com.capstone.app.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepositoryInterface userRepository;
    @Autowired
    private BCryptServiceInterface bCryptService;

    public UserService() {

    }
    @Override
    public void addUser(UserEntity userEntity) {
        userEntity.setPassword(bCryptService.hash(userEntity.getPassword()));
        userRepository.addUser(userEntity);
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public void updateUser(int id, String name) {

    }

    @Override
    public void getUser(int id) {

    }

    @Override
    public void getAllUsers() {

    }
}

package com.capstone.app.service;

import com.capstone.app.Exception.UserAlreadyExistsException;
import com.capstone.app.entity.UserEntity;
import com.capstone.app.repository.UserRepositoryInterface;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepositoryInterface userRepository;
    @Autowired
    private BCryptServiceInterface bCryptService;

    public UserService() {

    }
    @Override
    public void addUser(UserEntity userEntity) throws UserAlreadyExistsException {
        if (checkUserExists(userEntity.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        userEntity.setPassword(bCryptService.hash(userEntity.getPassword()));
        userRepository.addUser(userEntity);
    }

    @Override
    public boolean checkUserExists(String username) {
        return userRepository.getUserByUsername(username) != null;
    }

    @Override
    public boolean checkUser(String username, String password) {

        UserEntity userEntity = userRepository.getUserByUsername(username);
        if (userEntity == null) {
            return false;
        }
        String[] mutableHash = new String[1];
        mutableHash[0] = userEntity.getPassword();
        Function<String, Boolean> update = hash -> {
            mutableHash[0] = hash;
            return true;
        };
        if (bCryptService.verifyAndUpdateHash(password, userEntity.getPassword(), update)) {
            userEntity.setPassword(mutableHash[0]);
            userEntity.setCity("New York");
            userRepository.updateUser(userEntity);
            return true;
        }

        return false;

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
    public UserEntity getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserEntity getUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return null;
    }
}

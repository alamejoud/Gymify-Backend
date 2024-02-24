package com.capstone.app.service;

import com.capstone.app.entity.UserEntity;

public interface UserServiceInterface {

        public void addUser(UserEntity userEntity);
        public void deleteUser(int id);
        public void updateUser(int id, String name);
        public void getUser(int id);
        public void getAllUsers();
}

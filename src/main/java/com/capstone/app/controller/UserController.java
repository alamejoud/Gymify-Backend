package com.capstone.app.controller;

import com.capstone.app.Exception.UserAlreadyExistsException;
import com.capstone.app.entity.UserEntity;
import com.capstone.app.repository.UserRepository;
import com.capstone.app.service.UserService;
import com.capstone.app.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceInterface userService;
    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody UserEntity user) {
        try {
            userService.addUser(user);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", e.getMessage()));
        }
        Map<String, String> message = new HashMap<>();
        message.put("headerMessage", "User added successfully");
        message.put("bodyMessage", "You may now login with your credentials");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("message", message)); }

}

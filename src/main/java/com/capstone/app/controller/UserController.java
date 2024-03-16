package com.capstone.app.controller;

import com.capstone.app.exception.UserAlreadyExistsException;
import com.capstone.app.entity.UserEntity;
import com.capstone.app.service.JwtService;
import com.capstone.app.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtService jwtService;
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
                .body(Map.of("message", message));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserEntity user) {
        Map<String, Object> response = new HashMap<>();
        if (userService.checkUser(user.getUsername(), user.getPassword())) {
            response.put("token", jwtService.generateToken(user.getUsername()));
            response.put("message", "Login successful");
            response.put("user", userService.getUserByUsername(user.getUsername()));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
        response.put("message", "Invalid username or password");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleEmployeeNotFound(
            Exception exception
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "An error occurred, please try again later"));
    }

    @GetMapping("/getLoggedInUser")
    public ResponseEntity<Object> getLoggedInUser(@RequestParam String token) {
        if (jwtService.extractExpiration(token).before(new Date())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "Token has expired"));
        }
        String username = jwtService.extractUsername(token);
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "User not found"));
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("user", user));
    }

    @PostMapping("/uploadProfilePicture")
    public ResponseEntity<Object> uploadProfilePicture(@RequestParam("file") MultipartFile file, @RequestParam("token") String token) throws IOException, SQLException {
        if (jwtService.extractExpiration(token).before(new Date())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "Token has expired"));
        }
        String username = jwtService.extractUsername(token);
        UserEntity user = userService.getUserByUsername(username);
        user.setProfilePicture(file.getBytes());
        userService.updateUser(user);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("message", "Profile picture uploaded successfully"));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody UserEntity user) {
        userService.updateUser(user);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("message", "User updated successfully"));
    }

    @GetMapping("/getLoggedInProfilePicture")
    public ResponseEntity<Object> getLoggedInProfilePicture(@RequestParam String token) {
        String username = jwtService.extractUsername(token);
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "User not found"));
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("profilePicture", user.getProfilePicture()));
    }

}

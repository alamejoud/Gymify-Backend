package com.capstone.app.controller;

import com.capstone.app.exception.UserAlreadyExistsException;
import com.capstone.app.entity.UserEntity;
import com.capstone.app.service.JwtService;
import com.capstone.app.service.UserServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Blob;
import java.sql.Clob;
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
        Map<String, String> response = new HashMap<>();
        if (userService.checkUser(user.getUsername(), user.getPassword())) {
            response.put("token", jwtService.generateToken(user.getUsername()));
            response.put("message", "Login successful");
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

    @GetMapping("/getUser")
    public ResponseEntity<Object> getUser(@RequestParam String token) {
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
    public ResponseEntity<Object> uploadProfilePicture(@RequestParam("file") MultipartFile file) throws IOException {
        String username = "Admin";
        UserEntity user = userService.getUserByUsername(username);
        user.setProfilePicture(file.getBytes());
        userService.updateUser(user);
//        File myFile = new File("C:\\Users\\PC\\Desktop\\image.jpg");
//        byte[] byteArray = new byte[(int) myFile.length()];
//        try (FileInputStream inputStream = new FileInputStream(myFile)) {
//            inputStream.read(byteArray);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\PC\\Desktop\\imageNew.jpg")) {
//            fos.write(byteArray);
//            //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
//        }
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

}

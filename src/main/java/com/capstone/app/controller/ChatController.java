package com.capstone.app.controller;

import com.capstone.app.entity.MessageEntity;
import com.capstone.app.entity.UserEntity;
import com.capstone.app.service.ChatServiceInterface;
import com.capstone.app.service.UserServiceInterface;
import com.capstone.app.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatServiceInterface chatService;
    @Autowired
    private UserServiceInterface userService;

    @GetMapping("/getContacts")
    public ResponseEntity<Object> getContacts(@RequestHeader("Authorization") String token, @RequestParam String role, @RequestParam String search) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        List<UserEntity> contacts = chatService.getContacts(token, role, search);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("contacts", contacts));
    }

    @PostMapping("/saveMessage")
    public ResponseEntity<Object> saveMessage(@RequestHeader("Authorization") String token, @RequestBody MessageEntity messageEntity) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        messageEntity.setMessageFrom(userService.getUserByUsername(messageEntity.getMessageFromUsername()));
        messageEntity.setMessageTo(userService.getUserByUsername(messageEntity.getMessageToUsername()));
        chatService.saveMessage(messageEntity);
        return ResponseEntity.ok(Map.of("message", "Chat saved successfully."));
    }

    @GetMapping("/getMessages")
    public ResponseEntity<Object> getMessages(@RequestHeader("Authorization") String token, @RequestParam String username) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok(Map.of("messages", chatService.getMessages(token, username)));
    }

    @GetMapping("/markMessagesAsRead")
    public ResponseEntity<Object> markMessagesAsRead(@RequestHeader("Authorization") String token, @RequestParam String username) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        chatService.markMessagesAsRead(token, username);
        return ResponseEntity.ok(Map.of("message", "Messages marked as read."));
    }

    @GetMapping("/getUnreadMessages")
    public ResponseEntity<Object> getUnreadMessages(@RequestHeader("Authorization") String token, @RequestParam String username) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok(Map.of("unreadMessages", chatService.getUnreadMessages(token, username)));
    }

    @GetMapping("/getUnreadChats")
    public ResponseEntity<Object> getUnreadChats(@RequestHeader("Authorization") String token) {
        if (!CommonUtil.authenticateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired. Please login again."));
        }
        return ResponseEntity.ok(Map.of("unreadChats", chatService.getUnreadChats(token)));
    }
}

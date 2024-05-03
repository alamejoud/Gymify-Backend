package com.capstone.app.controller;

import com.capstone.app.entity.MessageEntity;
import com.capstone.app.entity.MessageFileEntity;
import com.capstone.app.service.ChatServiceInterface;
import com.capstone.app.service.JwtService;
import com.capstone.app.service.UserServiceInterface;
import com.capstone.app.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class WebSocketChatController {

    @Autowired
    private ChatServiceInterface chatService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send/message")
    public void saveMessage(String info) {
        MessageInfo messageInfo = CommonUtil.convertStringToObject(info, MessageInfo.class);
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(messageInfo.message);
        messageEntity.setMessageFrom(userService.getUserByUsername(jwtService.extractUsername(messageInfo.token).toLowerCase()));
        messageEntity.setMessageFromUsername(jwtService.extractUsername(messageInfo.token).toLowerCase());
        messageEntity.setMessageTo(userService.getUserByUsername(messageInfo.recipient));
        messageEntity.setMessageToUsername(messageInfo.recipient);
        messageEntity.setMessageDate(new Date());
        messageEntity.setMessageType(messageEntity.getMessageFiles() == null || messageEntity.getMessageFiles().isEmpty() ? "text" : "file");
        messageEntity.setMessageStatus("sent");
        messageEntity.setMessageFiles(messageInfo.files);
        messageEntity.getMessageFiles().forEach(file -> {
            if (file.getFileSrc() != null) {
                file.setFileSrcDb(file.getFileSrc().getBytes());
            }
            file.setMessage(messageEntity);
        });
        chatService.markMessagesAsRead(messageInfo.token, messageInfo.recipient);
        chatService.saveMessage(messageEntity);
        messagingTemplate.convertAndSend("/message",  messageEntity);
    }



    private class MessageInfo {

        private String token;
        private String message;
        private ArrayList<MessageFileEntity> files;
        private String recipient;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getRecipient() {
            return recipient;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }
    }

}

package com.capstone.app.service;

import com.capstone.app.entity.MessageEntity;
import com.capstone.app.entity.UserEntity;

import java.util.List;

public interface ChatServiceInterface {

    public void saveMessage(MessageEntity messageEntity);
    public List<MessageEntity> getMessages(String token, String username);
    public void markMessagesAsRead(String token, String username);
    public List<UserEntity> getContacts(String token, String role, String search);
    public int getUnreadChats(String token);
}

package com.capstone.app.repository;

import com.capstone.app.entity.MessageEntity;
import com.capstone.app.entity.UserEntity;

import java.util.List;

public interface ChatRepositoryInterface {

    public void saveMessage(MessageEntity messageEntity);
    public List<MessageEntity> getMessages(String username, String otherUsername);
    public void markMessagesAsRead(String username, String otherUsername);
    public List<UserEntity> getContacts(String username, String role, String search);
    public int getUnreadChats(String username);
}

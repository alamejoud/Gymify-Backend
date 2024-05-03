package com.capstone.app.service;

import com.capstone.app.entity.MessageEntity;
import com.capstone.app.entity.UserEntity;
import com.capstone.app.repository.ChatRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements ChatServiceInterface{

    @Autowired
    private ChatRepositoryInterface chatRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    public List<UserEntity> getContacts(String token, String role, String search) {
        List<UserEntity> contacts = chatRepository.getContacts(jwtService.extractUsername(token), role, search);
        contacts.forEach(contact -> {
            contact.setUnreadMessages((int) contact.getSentMessages().stream().filter(message -> message.getMessageTo().getUsername().toLowerCase().equals(jwtService.extractUsername(token).toLowerCase()) && message.getMessageStatus().equals("sent")).count());
        });
        contacts.forEach(contact -> {
            MessageEntity lastSentMessage = contact.getSentMessages().stream().filter(message -> message.getMessageTo().getUsername().toLowerCase().equals(jwtService.extractUsername(token).toLowerCase())).max((m1, m2) -> m1.getMessageDate().compareTo(m2.getMessageDate())).orElse(null);
            MessageEntity lastReceivedMessage = contact.getReceivedMessages().stream().filter(message -> message.getMessageFrom().getUsername().toLowerCase().equals(jwtService.extractUsername(token).toLowerCase())).max((m1, m2) -> m1.getMessageDate().compareTo(m2.getMessageDate())).orElse(null);
            if (lastSentMessage != null && lastReceivedMessage != null) {
                contact.setLastMessage(lastSentMessage.getMessageDate().compareTo(lastReceivedMessage.getMessageDate()) > 0 ? lastSentMessage : lastReceivedMessage);
            } else if (lastSentMessage != null) {
                contact.setLastMessage(lastSentMessage);
            } else if (lastReceivedMessage != null) {
                contact.setLastMessage(lastReceivedMessage);
            }
        });
        return contacts;
    }

    @Override
    public int getUnreadChats(String token) {
        return chatRepository.getUnreadChats(jwtService.extractUsername(token));
    }

    @Override
    public void saveMessage(MessageEntity messageEntity) {
        chatRepository.saveMessage(messageEntity);
    }

    @Override
    public List<MessageEntity> getMessages(String token, String username) {
        return chatRepository.getMessages(jwtService.extractUsername(token), username);
    }

    @Override
    public void markMessagesAsRead(String token, String username) {
        chatRepository.markMessagesAsRead(jwtService.extractUsername(token), username);
    }

}

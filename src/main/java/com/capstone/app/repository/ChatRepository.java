package com.capstone.app.repository;

import com.capstone.app.entity.MessageEntity;
import com.capstone.app.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ChatRepository implements ChatRepositoryInterface{

    @Autowired
    public EntityManager entityManager;

    @Override
    public List<UserEntity> getContacts(String username, String role, String search) {
        List<UserEntity> results = null;
        if (role.equals("admin")) {
            results = entityManager.createQuery("SELECT u FROM UserEntity u WHERE lower(u.username) <> lower(:username) AND lower(u.username) LIKE lower(:search)", UserEntity.class)
                    .setParameter("username", username)
                    .setParameter("search", "%" + search.trim() + "%")
                    .getResultList();
        } else if (role.equals("trainee")) {
            results = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.role <> 'trainee' AND lower(u.username) LIKE lower(:search)", UserEntity.class)
                    .setParameter("search", "%" + search.trim() + "%")
                    .getResultList();
        } else {
            results = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.role = 'trainee' or u.role = 'admin' AND lower(u.username) LIKE lower(:search)", UserEntity.class)
                    .setParameter("search", "%" + search.trim() + "%")
                    .getResultList();
        }
        return results;
    }

    @Override
    public int getUnreadChats(String username) {
        return entityManager.createQuery("SELECT COUNT(DISTINCT m.messageFrom) FROM MessageEntity m WHERE lower(m.messageTo.username) = lower(:username) AND m.messageStatus = 'sent'", Long.class)
                .setParameter("username", username)
                .getSingleResult()
                .intValue();
    }

    @Override
    public void saveMessage(MessageEntity messageEntity) {
        entityManager.persist(messageEntity);
    }

    @Override
    public List<MessageEntity> getMessages(String username, String otherUsername) {
        List<MessageEntity> messages = entityManager.createQuery("SELECT m FROM MessageEntity m join m.messageFrom f join m.messageTo t WHERE (lower(f.username) = lower(:username) AND lower(t.username) = lower(:otherUsername)) OR (lower(f.username) = lower(:otherUsername) AND lower(t.username) = lower(:username)) ORDER BY m.messageDate", MessageEntity.class)
                .setParameter("username", username)
                .setParameter("otherUsername", otherUsername)
                .getResultList();
        messages.forEach(message -> {
            message.getMessageFiles().forEach(file -> {
                if (file.getFileSrcDb() != null)
                    file.setFileSrc(new String(file.getFileSrcDb()));
            });
        });
        return messages;
    }

    @Override
    public void markMessagesAsRead(String username, String otherUsername) {
        entityManager.createNativeQuery("UPDATE G_MESSAGES SET message_status = 'read' WHERE EXISTS (SELECT 1 FROM G_USERS WHERE lower(username) = lower(:username) AND user_id = message_to) AND EXISTS (SELECT 1 FROM G_USERS WHERE lower(username) = lower(:otherUsername) AND user_id = message_from)")
                .setParameter("username", username)
                .setParameter("otherUsername", otherUsername)
                .executeUpdate();
    }

}

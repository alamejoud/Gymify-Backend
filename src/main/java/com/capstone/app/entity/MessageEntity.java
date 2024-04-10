package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.Date;
import java.util.List;

@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_MESSAGES")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_MESSAGES_SEQ")
    @SequenceGenerator(name = "G_MESSAGES_SEQ", sequenceName = "G_MESSAGES_SEQ", allocationSize = 1)
    @Column(name = "MESSAGE_ID")
    @Getter
    @Setter
    private int messageId;
    @Column(name = "MESSAGE")
    @Getter
    @Setter
    private String message;
    @Column(name = "MESSAGE_DATE")
    @Getter
    @Setter
    private Date messageDate;
    @Column(name = "MESSAGE_STATUS")
    @Getter
    @Setter
    private String messageStatus;
    @Column(name = "MESSAGE_TYPE")
    @Getter
    @Setter
    private String messageType;
    @ManyToOne
    @JoinColumn(name = "MESSAGE_FROM")
    @JsonIgnore
    @Getter
    @Setter
    private UserEntity messageFrom;
    @Formula(value = "(SELECT U.USERNAME FROM G_USERS U WHERE U.USER_ID = MESSAGE_FROM)")
    @Getter
    @Setter
    private String messageFromUsername;
    @ManyToOne
    @JoinColumn(name = "MESSAGE_TO")
    @JsonIgnore
    @Getter
    @Setter
    private UserEntity messageTo;
    @Formula(value = "(SELECT U.USERNAME FROM G_USERS U WHERE U.USER_ID = MESSAGE_TO)")
    @Getter
    @Setter
    private String messageToUsername;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "message", cascade = CascadeType.ALL)
    @Getter
    private List<MessageFileEntity> messageFiles;

    public void setMessageFiles(List<MessageFileEntity> messageFiles) {
        this.messageFiles = messageFiles;
        this.messageFiles.forEach(messageFileEntity -> messageFileEntity.setMessage(this));
    }
}

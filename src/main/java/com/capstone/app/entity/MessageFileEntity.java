package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_MESSAGE_FILES")
public class MessageFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_MESSAGE_FILES_SEQ")
    @SequenceGenerator(name = "G_MESSAGE_FILES_SEQ", sequenceName = "G_MESSAGE_FILES_SEQ", allocationSize = 1)
    @Column(name = "FILE_ID")
    @Getter
    @Setter
    private int fileId;
    @Column(name = "FILE_NAME")
    @Getter
    @Setter
    private String fileName;
    @Column(name = "FILE_TYPE")
    @Getter
    @Setter
    private String fileType;
    @Transient
    @Getter
    @Setter
    private String fileSrc;
    @Column(name = "FILE_SRC")
    @Getter
    private byte[] fileSrcDb;
    @Column(name = "FILE_ICON")
    @Getter
    @Setter
    private String fileIcon;
    @ManyToOne
    @JoinColumn(name = "MESSAGE_ID")
    @Getter
    @Setter
    @JsonIgnore
    private MessageEntity message;

    public void setFileSrc(String fileSrc) {
        this.fileSrc = fileSrc;
        if (fileSrc != null)
            this.fileSrcDb = fileSrc.getBytes();
    }
    public void setFileSrcDb(byte[] fileSrcDb) {
        this.fileSrcDb = fileSrcDb;
        if (fileSrcDb != null)
            this.fileSrc = new String(fileSrcDb);
    }

}

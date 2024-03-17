package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_TYPES")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_TYPES_SEQ")
    @SequenceGenerator(name = "G_TYPES_SEQ", sequenceName = "G_TYPES_SEQ", allocationSize = 1)
    @Column(name = "TYPE_ID")
    private int typeId;
    @Column(name = "TYPE_NAME")
    private String typeName;
    @Lob
    @Basic
    @Column(name = "TYPE_PICTURE", columnDefinition = "BLOB")
    private byte[] typePicture;
}

package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_EQUIPMENTS")
public class EquipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_EQUIPMENTS_SEQ")
    @SequenceGenerator(name = "G_EQUIPMENTS_SEQ", sequenceName = "G_EQUIPMENTS_SEQ", allocationSize = 1)
    @Column(name = "EQUIPMENT_ID")
    private int equipmentId;
    @Column(name = "EQUIPMENT_NAME")
    private String equipmentName;
    @Lob
    @Basic
    @Column(name = "EQUIPMENT_PICTURE", columnDefinition = "BLOB")
    private byte[] equipmentPicture;
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "equipments")
    private List<ExerciseEntity> exercises = new ArrayList<>();

}

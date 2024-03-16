package com.capstone.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_EXERCISE_EQUIPMENTS")
public class ExerciseEquipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_EXERCISE_EQUIPMENTS_SEQ")
    @SequenceGenerator(name = "G_EXERCISE_EQUIPMENTS_SEQ", sequenceName = "G_EXERCISE_EQUIPMENTS_SEQ", allocationSize = 1)
    @Column(name = "EQUIPMENT_ID")
    private int equipmentId;
    @Column(name = "EQUIPMENT")
    private String equipment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_ID")
    private ExerciseEntity exercise;

}

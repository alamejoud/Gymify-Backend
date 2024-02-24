package com.capstone.app.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_USERS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_USERS_SEQ")
    @SequenceGenerator(name = "G_USERS_SEQ", sequenceName = "G_USERS_SEQ", allocationSize = 1)
    @Column(name = "USER_ID")
    private int userId;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Transient
    private String confirmPassword;
    @Column(name = "USER_EMAIL")
    private String email;
    @Column(name = "USER_FIRST_NAME")
    private String firstName;
    @Column(name = "USER_LAST_NAME")
    private String lastName;
    @Column(name = "USER_ADDRESS")
    private String address;
    @Column(name = "USER_CITY")
    private String city;
    @Column(name = "USER_PHONE")
    private String phoneNumber;
    @Column(name = "USER_ROLE")
    private String role;
    @Column(name = "USER_STATUS")
    private String status;

}

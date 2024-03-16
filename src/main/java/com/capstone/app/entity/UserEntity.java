package com.capstone.app.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.sql.Blob;

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
    @Formula(value = "(SELECT L.LOOKUP_NAME FROM G_LOOKUPS L WHERE L.LOOKUP_TYPE = 'USER_ROLE' AND L.LOOKUP_CODE = USER_ROLE)")
    private String roleName;
    @Column(name = "USER_STATUS")
    private String status;
    @Formula(value = "(SELECT L.LOOKUP_NAME FROM G_LOOKUPS L WHERE L.LOOKUP_TYPE = 'USER_STATUS' AND L.LOOKUP_CODE = USER_STATUS)")
    private String statusName;
    @Column(name="USER_TITLE")
    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "PROFILE_PICTURE", columnDefinition = "BLOB")
    private byte[] profilePicture;

}

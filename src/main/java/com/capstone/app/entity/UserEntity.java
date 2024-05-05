package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_USERS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_USERS_SEQ")
    @SequenceGenerator(name = "G_USERS_SEQ", sequenceName = "G_USERS_SEQ", allocationSize = 1)
    @Column(name = "USER_ID")
    @Getter
    @Setter
    private int userId;
    @Column(name = "USERNAME")
    @Getter
    @Setter
    private String username;
    @Column(name = "USER_PASSWORD")
    @Getter
    @Setter
    private String password;
    @Transient
    @Getter
    @Setter
    private String confirmPassword;
    @Column(name = "USER_EMAIL")
    @Getter
    @Setter
    private String email;
    @Column(name = "USER_FIRST_NAME")
    @Getter
    @Setter
    private String firstName;
    @Column(name = "USER_LAST_NAME")
    @Getter
    @Setter
    private String lastName;
    @Column(name = "USER_ADDRESS")
    @Getter
    @Setter
    private String address;
    @Column(name = "USER_CITY")
    @Getter
    @Setter
    private String city;
    @Column(name = "USER_PHONE")
    @Getter
    @Setter
    private String phoneNumber;
    @Column(name = "USER_ROLE")
    @Getter
    @Setter
    private String role;
    @Formula(value = "(SELECT L.LOOKUP_NAME FROM G_LOOKUPS L WHERE L.LOOKUP_TYPE = 'USER_ROLE' AND L.LOOKUP_CODE = USER_ROLE)")
    @Getter
    @Setter
    private String roleName;
    @Column(name = "USER_STATUS")
    @Getter
    @Setter
    private String status;
    @Formula(value = "(SELECT L.LOOKUP_NAME FROM G_LOOKUPS L WHERE L.LOOKUP_TYPE = 'USER_STATUS' AND L.LOOKUP_CODE = USER_STATUS)")
    @Getter
    @Setter
    private String statusName;
    @Column(name="USER_TITLE")
    @Getter
    @Setter
    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "PROFILE_PICTURE", columnDefinition = "BLOB")
    @Getter
    @Setter
    private byte[] profilePicture;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "createdBy")
    @Getter
    @Setter
    private List<ExerciseEntity> createdExercises = new ArrayList<>();
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<WorkoutEntity> workouts = new ArrayList<>();
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<DietEntity> diets = new ArrayList<>();
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "messageFrom")
    @Getter
    @Setter
    private List<MessageEntity> sentMessages = new ArrayList<>();
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "messageTo")
    @Getter
    @Setter
    private List<MessageEntity> receivedMessages = new ArrayList<>();
    @Transient
    @Getter
    @Setter
    private int unreadMessages;
    @Transient
    @Getter
    @Setter
    private MessageEntity lastMessage;

    public void setWorkouts(List<WorkoutEntity> workouts) {
        this.workouts.clear();
        workouts.forEach(workout -> this.workouts.add(workout));
        workouts.forEach(workout -> workout.getUsers().add(this));
    }

}

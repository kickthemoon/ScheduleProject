package com.study.schedule.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.schedule.config.localDateTime.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    // config > localDateTime > BaseEntity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    private String email;

    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void updateUsernameAndEmail(String username, String email) {
        this.username = username;
        this.email = email;
    }
}

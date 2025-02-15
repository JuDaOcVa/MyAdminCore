package com.judaocva.myadmincore.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class UsersEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name = "PK_USERS", unique = true, updatable = false, nullable = false)
    private UUID pkUsers;

    @JoinColumn(name = "IDENTIFICATION_CARD")
    private String identificationCard;

    @JoinColumn(name = "NAME")
    private String name;

    @JoinColumn(name = "EMAIL")
    private String email;

    @JoinColumn(name = "PASSWORD")
    private String password;

    @JoinColumn(name = "PHONE")
    private String phone;

    @JoinColumn(name = "ADDRESS")
    private String address;

    @JoinColumn(name = "LOGIN_ATTEMPTS_FAILED")
    private int loginAttemptsFailed;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_STATUS",nullable = false)
    private StatusEntity fkStatus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_ROLES",nullable = false)
    private RolesEntity fkRoles;

    @JoinColumn(name = "CREATED_AT")
    private Date createdAt;

    @JoinColumn(name = "UPDATED_AT")
    private Date updatedAt;
}

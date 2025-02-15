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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SESSIONS")
public class SessionsEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name = "PK_SESSIONS", updatable = false, nullable = false)
    private UUID pkSessions;

    @JoinColumn(name = "FK_USERS")
    @OneToOne(fetch = FetchType.EAGER)
    private UsersEntity fkUsers;

    @JoinColumn(name = "TOKEN")
    private String token;

    @JoinColumn(name = "REFRESH_TOKEN")
    private String refreshToken;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_STATUS",nullable = false)
    private StatusEntity fkStatus;

    @JoinColumn(name = "CREATED_AT")
    private Date createdAt;

    @JoinColumn(name = "UPDATED_AT")
    private Date updatedAt;
}

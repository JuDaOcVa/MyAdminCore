package com.judaocva.myadmincore.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROLES")
public class RolesEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name = "PK_ROLES", unique = true, updatable = false, nullable = false)
    private UUID pkRoles;

    @JoinColumn(name = "DESCRIPTION")
    private String description;

}

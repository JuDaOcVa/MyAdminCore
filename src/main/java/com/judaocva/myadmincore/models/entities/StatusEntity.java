package com.judaocva.myadmincore.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STATUS")
public class StatusEntity {

    @Id
    @Column(name = "PK_STATUS", unique = true, updatable = false, nullable = false)
    private Long pkStatus;

    @JoinColumn(name = "DESCRIPTION")
    private String description;
}

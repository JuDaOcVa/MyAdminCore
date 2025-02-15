package com.judaocva.myadmincore.models.dtos.users;

import com.judaocva.myadmincore.models.entities.RolesEntity;
import com.judaocva.myadmincore.models.entities.StatusEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {

    private UUID pkUsers;
    private String identificationCard;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private int loginAttemptsFailed;
    private StatusEntity fkStatus;
    private RolesEntity fkRoles;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return "UsersResponse{" +
                "pkUsers=" + pkUsers +
                ", identificationCard='" + identificationCard + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", loginAttemptsFailed=" + loginAttemptsFailed +
                ", fkStatus=" + fkStatus +
                ", fkRoles=" + fkRoles +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

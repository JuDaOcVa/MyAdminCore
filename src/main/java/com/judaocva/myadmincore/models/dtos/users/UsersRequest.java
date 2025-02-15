package com.judaocva.myadmincore.models.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequest {

    String identificationCard;

    String name;

    String email;

    String password;

    String phone;

    String address;

    UUID fkRoles;

    @Override
    public String toString() {
        return "UsersRequest{" +
                "identificationCard='" + identificationCard + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", fkRoles=" + fkRoles +
                '}';
    }
}
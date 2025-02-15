package com.judaocva.myadmincore.models.dtos.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String identificationCard;

    private String password;

    @Override
    public String toString() {
        return "LoginRequest{" +
                "identificationCard='" + identificationCard + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

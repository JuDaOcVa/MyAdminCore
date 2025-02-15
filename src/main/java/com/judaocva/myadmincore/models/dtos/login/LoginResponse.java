package com.judaocva.myadmincore.models.dtos.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

    private String token;

    private String refreshToken;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}

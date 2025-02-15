package com.judaocva.myadmincore.services.auth;

import com.judaocva.myadmincore.models.dtos.GenericResponseDto;
import com.judaocva.myadmincore.models.dtos.login.LoginRequest;

public interface AuthService {

    GenericResponseDto login(LoginRequest loginRequest);

    GenericResponseDto logout(String refreshToken);

    GenericResponseDto refreshToken(String refreshToken);
}

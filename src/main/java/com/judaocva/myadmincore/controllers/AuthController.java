package com.judaocva.myadmincore.controllers;

import com.judaocva.myadmincore.models.dtos.GenericResponseDto;
import com.judaocva.myadmincore.models.dtos.login.LoginRequest;
import com.judaocva.myadmincore.services.auth.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/myadmin/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public GenericResponseDto login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PutMapping("/logout")
    public GenericResponseDto logout(@RequestHeader("Authorization") String refreshToken) {
        return authService.logout(refreshToken);
    }

    @PutMapping("/refreshToken")
    public GenericResponseDto refreshToken(@RequestHeader("Authorization") String refreshToken) {
        return authService.refreshToken(refreshToken);
    }

}
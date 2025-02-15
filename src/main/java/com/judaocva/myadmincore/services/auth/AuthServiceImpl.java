package com.judaocva.myadmincore.services.auth;

import com.judaocva.myadmincore.config.security.JwtUtil;
import com.judaocva.myadmincore.config.security.SecurityConfig;
import com.judaocva.myadmincore.miscellaneous.Constants;
import com.judaocva.myadmincore.models.dtos.GenericResponseDto;
import com.judaocva.myadmincore.models.dtos.login.LoginRequest;
import com.judaocva.myadmincore.models.dtos.login.LoginResponse;
import com.judaocva.myadmincore.models.entities.SessionsEntity;
import com.judaocva.myadmincore.models.entities.UsersEntity;
import com.judaocva.myadmincore.repositories.SessionsRepository;
import com.judaocva.myadmincore.repositories.StatusRepository;
import com.judaocva.myadmincore.repositories.UsersRepository;
import com.judaocva.myadmincore.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;

    private final UsersRepository usersRepository;

    private final SessionsRepository sessionsRepository;

    private final StatusRepository statusRepository;

    private final MessageService messageService;

    private final SecurityConfig securityConfig;

    public AuthServiceImpl(JwtUtil jwtUtil,
                           UsersRepository usersRepository,
                           SessionsRepository sessionsRepository,
                           StatusRepository statusRepository,
                           MessageService messageService,
                           SecurityConfig securityConfig) {
        this.jwtUtil = jwtUtil;
        this.usersRepository = usersRepository;
        this.sessionsRepository = sessionsRepository;
        this.statusRepository = statusRepository;
        this.messageService = messageService;
        this.securityConfig = securityConfig;
    }

    public GenericResponseDto login(LoginRequest loginRequest) {
        GenericResponseDto response = new GenericResponseDto();
        try {
            if (loginRequest.getIdentificationCard().isBlank() || loginRequest.getPassword().isBlank()) {
                response.setSuccess(false);
                response.setMessage(messageService.getMessage("login.idAndPasswordRequired"));
                return response;
            }

            UsersEntity userEntity = usersRepository.findByIdentificationCard(loginRequest.getIdentificationCard());

            if (userEntity == null) {
                response.setSuccess(false);
                response.setMessage(messageService.getMessage("user.notFound"));
                return response;
            }

            if (!securityConfig.passwordEncoder().matches(loginRequest.getPassword(), userEntity.getPassword())) {
                response.setSuccess(false);
                response.setMessage(messageService.getMessage("login.invalidCredentials"));
                return response;
            }

            String token = jwtUtil.generateToken(loginRequest.getIdentificationCard());
            String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getIdentificationCard());

            SessionsEntity newSession = new SessionsEntity();
            newSession.setFkUsers(userEntity);
            newSession.setToken(token);
            newSession.setRefreshToken(refreshToken);
            newSession.setFkStatus(statusRepository.findByPkStatus(Constants.STATUS_ACTIVE));
            newSession.setCreatedAt(new Date());

            sessionsRepository.save(newSession);

            response.setSuccess(true);
            response.setMessage(messageService.getMessage("login.success"));

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setRefreshToken(refreshToken);

            response.setData(loginResponse);

            return response;
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(messageService.getMessage("login.error", e.getMessage()));
            return response;
        }
    }

    public GenericResponseDto logout(String refreshToken) {
        GenericResponseDto response = new GenericResponseDto();
        try {
            GenericResponseDto validateTokensResponse = this.validateTokens(refreshToken);
            if (!this.validateTokens(refreshToken).isSuccess()) {
                response.setSuccess(false);
                response.setMessage(validateTokensResponse.getMessage());
                return response;
            }
            SessionsEntity session = (SessionsEntity) validateTokensResponse.getData();

            session.setFkStatus(statusRepository.findByPkStatus(Constants.STATUS_INACTIVE));
            session.setUpdatedAt(new Date());
            sessionsRepository.save(session);

            response.setSuccess(true);
            response.setMessage(messageService.getMessage("logout.success"));

            return response;
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(messageService.getMessage("logout.error", e.getMessage()));
            return response;
        }
    }

    public GenericResponseDto refreshToken(String refreshToken) {
        GenericResponseDto response = new GenericResponseDto();
        try {
            GenericResponseDto validateTokensResponse = this.validateTokens(refreshToken);
            if (!this.validateTokens(refreshToken).isSuccess()) {
                response.setSuccess(false);
                response.setMessage(validateTokensResponse.getMessage());
                return response;
            }
            SessionsEntity session = (SessionsEntity) validateTokensResponse.getData();

            String newRefreshToken = jwtUtil.generateRefreshToken(session.getFkUsers().getIdentificationCard());
            session.setRefreshToken(newRefreshToken);
            session.setUpdatedAt(new Date());
            sessionsRepository.save(session);

            response.setSuccess(true);
            response.setMessage(messageService.getMessage("session.refreshToken.success"));

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setRefreshToken(newRefreshToken);

            response.setData(loginResponse);

            return response;
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(messageService.getMessage("session.refreshToken.error", e.getMessage()));
            return response;
        }
    }

    public GenericResponseDto validateTokens(String refreshToken) {

        GenericResponseDto response = new GenericResponseDto();
        try {
            SessionsEntity session = sessionsRepository.findByRefreshToken(refreshToken);

            if (session == null) {
                response.setSuccess(false);
                response.setMessage(messageService.getMessage("session.notFound"));
                return response;
            }

            if (session.getFkStatus().getPkStatus() != Constants.STATUS_ACTIVE) {
                response.setSuccess(false);
                response.setMessage(messageService.getMessage("session.inactive"));
                return response;
            }

            if (jwtUtil.validateToken(session.getToken()) == null || jwtUtil.validateRefreshToken(refreshToken) == null) {
                response.setSuccess(false);
                response.setMessage(messageService.getMessage("session.invalidTokens"));
                return response;
            }

            response.setSuccess(true);
            response.setData(session);
            response.setMessage(messageService.getMessage("session.validAndActive"));

            return response;
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(messageService.getMessage("session.validateTokens.error", e.getMessage()));
            return response;
        }
    }
}

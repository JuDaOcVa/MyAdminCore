package com.judaocva.myadmincore.services.users;

import com.judaocva.myadmincore.config.security.SecurityConfig;
import com.judaocva.myadmincore.miscellaneous.Constants;
import com.judaocva.myadmincore.models.dtos.GenericResponseDto;
import com.judaocva.myadmincore.models.dtos.users.UsersRequest;
import com.judaocva.myadmincore.models.entities.RolesEntity;
import com.judaocva.myadmincore.models.entities.UsersEntity;
import com.judaocva.myadmincore.models.mapper.UsersMapper;
import com.judaocva.myadmincore.repositories.RolesRepository;
import com.judaocva.myadmincore.repositories.StatusRepository;
import com.judaocva.myadmincore.repositories.UsersRepository;
import com.judaocva.myadmincore.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final StatusRepository statusRepository;
    private final UsersMapper usersMapper;
    private final MessageService messageService;
    private final SecurityConfig securityConfig;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository,
                            RolesRepository rolesRepository,
                            StatusRepository statusRepository,
                            UsersMapper usersMapper,
                            MessageService messageService,
                            SecurityConfig securityConfig) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.statusRepository = statusRepository;
        this.usersMapper = usersMapper;
        this.messageService = messageService;
        this.securityConfig = securityConfig;
    }

    @Override
    public GenericResponseDto save(UsersRequest request) {
        GenericResponseDto response = new GenericResponseDto();
        try {
            UsersEntity entity = usersMapper.toEntity(request);
            entity.setLoginAttemptsFailed(0);
            entity.setFkStatus(statusRepository.findByPkStatus(Constants.STATUS_ACTIVE));
            entity.setCreatedAt(Date.from(new Date().toInstant()));
            entity.setUpdatedAt(Date.from(new Date().toInstant()));
            build(entity, request);

            usersRepository.save(entity);
            response.setSuccess(true);
            response.setMessage(messageService.getMessage("user.saved"));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(messageService.getMessage("user.save.error", e.getMessage()));
        }
        return response;
    }

    @Override
    public GenericResponseDto update(UUID id, UsersRequest request) {
        GenericResponseDto response = new GenericResponseDto();
        try {
            UsersEntity entity = usersRepository.findById(id).orElse(null);
            if (entity != null) {
                usersMapper.updateEntityFromRequest(request, entity);
                build(entity, request);
                usersRepository.save(entity);
                response.setSuccess(true);
                response.setMessage(messageService.getMessage("user.updated"));
            } else {
                response.setSuccess(false);
                response.setMessage(messageService.getMessage("user.notFound"));
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(messageService.getMessage("user.update.error", e.getMessage()));
        }
        return response;
    }

    @Override
    public GenericResponseDto findById(UUID id) {
        GenericResponseDto response = new GenericResponseDto();
        try {
            UsersEntity entity = usersRepository.findById(id).orElse(null);
            if (entity != null) {
                response.setSuccess(true);
                response.setData(usersMapper.toDto(entity));
            } else {
                response.setSuccess(false);
                response.setMessage(messageService.getMessage("user.notFound"));
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(messageService.getMessage("user.find.error", e.getMessage()));
        }
        return response;
    }

    @Override
    public GenericResponseDto findAll() {
        GenericResponseDto response = new GenericResponseDto();
        try {
            response.setSuccess(true);
            response.setData(usersMapper.toDtoList(usersRepository.findAll()));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(messageService.getMessage("user.find.error", e.getMessage()));
        }
        return response;
    }

    @Override
    public GenericResponseDto deleteById(UUID id) {
        GenericResponseDto response = new GenericResponseDto();
        try {
            usersRepository.deleteById(id);
            response.setSuccess(true);
            response.setMessage(messageService.getMessage("user.deleted"));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(messageService.getMessage("user.delete.error", e.getMessage()));
        }
        return response;
    }

    private void build(UsersEntity entity, UsersRequest request) {
        RolesEntity rolesEntity = rolesRepository.findById(request.getFkRoles()).orElse(null);
        entity.setFkRoles(rolesEntity);
        if (request.getPassword() != null) {
            entity.setPassword(securityConfig.passwordEncoder().encode(request.getPassword()));
        }
    }
}
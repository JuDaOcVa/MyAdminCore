package com.judaocva.myadmincore.models.mapper;

import com.judaocva.myadmincore.models.dtos.users.UsersRequest;
import com.judaocva.myadmincore.models.dtos.users.UsersResponse;
import com.judaocva.myadmincore.models.entities.RolesEntity;
import com.judaocva.myadmincore.models.entities.UsersEntity;
import com.judaocva.myadmincore.repositories.RolesRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {UsersMapper.RolesMapper.class})
public interface UsersMapper extends GenericMapper<UsersEntity, UsersRequest, UsersResponse> {

    @Component
    class RolesMapper {
        private final RolesRepository rolesRepository;

        @Autowired
        public RolesMapper(RolesRepository rolesRepository) {
            this.rolesRepository = rolesRepository;
        }

        public RolesEntity map(UUID value) {
            return rolesRepository.findById(value).orElse(null);
        }
    }
}
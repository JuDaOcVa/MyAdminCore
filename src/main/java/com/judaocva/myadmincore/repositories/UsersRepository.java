package com.judaocva.myadmincore.repositories;

import com.judaocva.myadmincore.models.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, UUID>, JpaSpecificationExecutor<UsersEntity> {

    UsersEntity findByIdentificationCard(String identificationCard);

}

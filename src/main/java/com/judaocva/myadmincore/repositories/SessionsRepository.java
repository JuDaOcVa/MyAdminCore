package com.judaocva.myadmincore.repositories;

import com.judaocva.myadmincore.models.entities.SessionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionsRepository extends JpaRepository<SessionsEntity, UUID>, JpaSpecificationExecutor<SessionsEntity> {

    SessionsEntity findByRefreshToken(String token);
}

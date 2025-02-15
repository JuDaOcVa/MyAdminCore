package com.judaocva.myadmincore.repositories;

import com.judaocva.myadmincore.models.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, UUID>, JpaSpecificationExecutor<RolesEntity> {

}

package com.judaocva.myadmincore.repositories;

import com.judaocva.myadmincore.models.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long>, JpaSpecificationExecutor<StatusEntity> {

    StatusEntity findByPkStatus(Long pkStatus);
}

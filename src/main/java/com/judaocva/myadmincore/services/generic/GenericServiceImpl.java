package com.judaocva.myadmincore.services.generic;

import com.judaocva.myadmincore.models.dtos.GenericResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericServiceImpl<R, ID> implements GenericService<R, ID> {

    protected abstract JpaRepository<R, ID> getRepository();

    @Override
    public GenericResponseDto save(R request) {
        return new GenericResponseDto();
    }

    @Override
    public GenericResponseDto update(ID id, R request) {
        return new GenericResponseDto();
    }

    @Override
    public GenericResponseDto findById(ID id) {
        return new GenericResponseDto();
    }

    @Override
    public GenericResponseDto findAll() {
        return new GenericResponseDto();
    }

    @Override
    public GenericResponseDto deleteById(ID id) {
        return new GenericResponseDto();
    }
}
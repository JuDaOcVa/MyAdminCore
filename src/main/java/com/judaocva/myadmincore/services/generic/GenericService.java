package com.judaocva.myadmincore.services.generic;

import com.judaocva.myadmincore.models.dtos.GenericResponseDto;

public interface GenericService<R, ID> {

    GenericResponseDto save(R request);

    GenericResponseDto update(ID id, R request);

    GenericResponseDto findById(ID id);

    GenericResponseDto findAll();

    GenericResponseDto deleteById(ID id);
}
package com.judaocva.myadmincore.models.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface GenericMapper<E, R, D> {

    /**
     * Convierte una entidad a un DTO.
     *
     * @param entity la entidad a convertir
     * @return el DTO resultante
     */
    D toDto(E entity);

    /**
     * Convierte un Request a una entidad.
     *
     * @param request el Request a convertir
     * @return la entidad resultante
     */
    E toEntity(R request);

    /**
     * Convierte una lista de entidades a una lista de DTOs.
     *
     * @param entityList la lista de entidades a convertir
     * @return la lista de DTOs resultante
     */
    List<D> toDtoList(List<E> entityList);

    /**
     * Actualiza una entidad a partir de un Request.
     *
     * @param request el request con los nuevos datos
     * @param entity  la entidad a actualizar
     */
    void updateEntityFromRequest(R request, @MappingTarget E entity);
}
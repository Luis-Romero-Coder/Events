package com.catalogo.catalogo_eventos.infrastructure.mapper;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.infrastructure.adapters.in.web.dto.EventRequest;
import com.catalogo.catalogo_eventos.infrastructure.adapters.in.web.dto.EventResponse;
import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {

    // DTO -> Domain
    Event toDomain(EventRequest request);

    // Domain -> DTO
    EventResponse toResponse(Event event);

    // Domain -> Entity
    EventEntity toEntity(Event event);

    // Entity -> Domain
    Event toDomain(EventEntity entity);

    // optional helper to update entity from domain
    void updateEntityFromDomain(Event event, @MappingTarget EventEntity entity);
}

package com.catalogo.catalogo_eventos.infrastructure.mapper;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel=  "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventEntity toEntity(Event domain);
    Event toDomain(EventEntity entity);
}

package com.catalogo.catalogo_eventos.infrastructure.mapper;

import com.catalogo.catalogo_eventos.domain.model.Venue;
import com.catalogo.catalogo_eventos.infrastructure.adapters.in.web.dto.VenueRequest;
import com.catalogo.catalogo_eventos.infrastructure.adapters.in.web.dto.VenueResponse;
import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.entity.VenueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    Venue toDomain(VenueRequest request);

    VenueResponse toResponse(Venue venue);

    VenueEntity toEntity(Venue venue);

    Venue toDomain(VenueEntity entity);

    void updateEntityFromDomain(Venue venue, @MappingTarget VenueEntity entity);
}

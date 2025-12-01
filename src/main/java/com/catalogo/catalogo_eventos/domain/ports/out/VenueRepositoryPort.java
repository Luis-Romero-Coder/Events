package com.catalogo.catalogo_eventos.domain.ports.out;

import java.util.Optional;

import com.catalogo.catalogo_eventos.domain.model.PageRequests;
import com.catalogo.catalogo_eventos.domain.model.PaginatedResult;
import com.catalogo.catalogo_eventos.domain.model.Venue;

public interface VenueRepositoryPort {

    Venue save(Venue venue);

    Optional<Venue> findById(Long id);

    void deleteById(Long id);

    boolean existsByName(String name);

    PaginatedResult<Venue> findByNameContaining(String name, PageRequests pageRequest);
}

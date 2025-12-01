package com.catalogo.catalogo_eventos.domain.ports.out;

import java.util.Optional;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.model.PageRequests;
import com.catalogo.catalogo_eventos.domain.model.PaginatedResult;

public interface EventRepositoryPort {

    Event save(Event event);

    Optional<Event> findById(Long id);

    void deleteById(Long id);

    boolean existsByName(String name);

    PaginatedResult<Event> findByNameContaining(String name, PageRequests pageRequest);
}

package com.catalogo.catalogo_eventos.domain.ports.out;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.catalogo.catalogo_eventos.domain.model.Event;

public interface EventRepositoryPort {
    Event save(Event event);
    Optional<Event> findById(Long id);
    void deleteById(Long id);
    boolean existsByName(String name);
    Page<Event> findAllByNameContaining(String name, Pageable pageable);
}

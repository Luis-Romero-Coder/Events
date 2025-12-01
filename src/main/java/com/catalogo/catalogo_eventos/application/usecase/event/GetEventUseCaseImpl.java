package com.catalogo.catalogo_eventos.application.usecase.event;

import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.ports.in.GetEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;
import com.catalogo.catalogo_eventos.infrastructure.exception.NotFoundException;

@Service
public class GetEventUseCaseImpl implements GetEventUseCase {

    private final EventRepositoryPort repository;

    public GetEventUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Event getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
    }
}

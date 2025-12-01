package com.catalogo.catalogo_eventos.application.usecase.event;

import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.ports.in.UpdateEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;
import com.catalogo.catalogo_eventos.infrastructure.exception.NotFoundException;

@Service
public class UpdateEventUseCaseImpl implements UpdateEventUseCase {

    private final EventRepositoryPort repository;

    public UpdateEventUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Event update(Long id, Event event) {
        Event existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));

        existing.setName(event.getName());
        existing.setDescription(event.getDescription());
        existing.setDate(event.getDate());

        return repository.save(existing);
    }
}

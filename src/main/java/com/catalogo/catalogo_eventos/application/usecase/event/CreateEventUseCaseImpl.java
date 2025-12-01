package com.catalogo.catalogo_eventos.application.usecase.event;

import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.exception.BusinessRuleException;
import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.ports.in.CreateEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;

@Service
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final EventRepositoryPort repository;

    public CreateEventUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Event create(Event event) {
        if (event.getName() == null || event.getName().trim().isEmpty()) {
            throw new BusinessRuleException("Event name must not be empty");
        }
        if (repository.existsByName(event.getName())) {
            throw new BusinessRuleException("Event with the same name already exists");
        }
        return repository.save(event);
    }
}

package com.catalogo.catalogo_eventos.application.usecase.event;

import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.ports.in.DeleteEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;

@Service
public class DeleteEventUseCaseImpl implements DeleteEventUseCase {

    private final EventRepositoryPort repository;

    public DeleteEventUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

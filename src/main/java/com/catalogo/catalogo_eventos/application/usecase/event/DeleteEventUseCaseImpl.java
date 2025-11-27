package com.catalogo.catalogo_eventos.application.usecase.event;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalogo.catalogo_eventos.domain.ports.in.DeleteEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;
@Service
public class DeleteEventUseCaseImpl implements DeleteEventUseCase {
    private final EventRepositoryPort eventRepository;

    public DeleteEventUseCaseImpl(EventRepositoryPort eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public void delete(Long id){
        eventRepository.deleteById(id);
    }
}

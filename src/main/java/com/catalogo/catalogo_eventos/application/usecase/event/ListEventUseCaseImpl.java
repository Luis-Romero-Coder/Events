package com.catalogo.catalogo_eventos.application.usecase.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.ports.in.ListEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;
@Service
public class ListEventUseCaseImpl implements ListEventUseCase {
    private final EventRepositoryPort eventRepository;

    public ListEventUseCaseImpl(EventRepositoryPort eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public Page<Event> list(String nameFilter, Pageable pageable){
        String filter = nameFilter == null ? "" : nameFilter;
        return eventRepository.findAllByNameContaining(filter, pageable);
    }
}

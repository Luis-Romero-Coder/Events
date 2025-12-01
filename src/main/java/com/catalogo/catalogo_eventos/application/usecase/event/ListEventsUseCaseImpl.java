package com.catalogo.catalogo_eventos.application.usecase.event;

import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.model.PageRequests;
import com.catalogo.catalogo_eventos.domain.model.PaginatedResult;
import com.catalogo.catalogo_eventos.domain.ports.in.ListEventsUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;

@Service
public class ListEventsUseCaseImpl implements ListEventsUseCase {

    private final EventRepositoryPort repository;

    public ListEventsUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public PaginatedResult<Event> list(String nameFilter, PageRequests pageRequest) {
        String effective = (nameFilter == null) ? "" : nameFilter;
        PageRequests effectivePageRequest = (pageRequest == null) ? new PageRequests(0, 10, null) : pageRequest;
        return repository.findByNameContaining(effective, effectivePageRequest);
    }
}

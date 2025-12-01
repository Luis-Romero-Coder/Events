package com.catalogo.catalogo_eventos.application.usecase.venue;

import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.model.PageRequests;
import com.catalogo.catalogo_eventos.domain.model.PaginatedResult;
import com.catalogo.catalogo_eventos.domain.model.Venue;
import com.catalogo.catalogo_eventos.domain.ports.in.ListVenuesUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.VenueRepositoryPort;

@Service
public class ListVenuesUseCaseImpl implements ListVenuesUseCase {

    private final VenueRepositoryPort repository;

    public ListVenuesUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public PaginatedResult<Venue> list(String nameFilter, PageRequests pageRequest) {
        PageRequests effective = (pageRequest == null) ? new PageRequests(0, 10, null) : pageRequest;
        return repository.findByNameContaining(nameFilter == null ? "" : nameFilter, effective);
    }
}

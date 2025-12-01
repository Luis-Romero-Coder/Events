package com.catalogo.catalogo_eventos.application.usecase.venue;

import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.model.Venue;
import com.catalogo.catalogo_eventos.domain.ports.in.GetVenueUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.VenueRepositoryPort;
import com.catalogo.catalogo_eventos.infrastructure.exception.NotFoundException;

@Service
public class GetVenueUseCaseImpl implements GetVenueUseCase {

    private final VenueRepositoryPort repository;

    public GetVenueUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Venue getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venue not found with id: " + id));
    }
}

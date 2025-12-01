package com.catalogo.catalogo_eventos.application.usecase.venue;

import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.model.Venue;
import com.catalogo.catalogo_eventos.domain.ports.in.UpdateVenueUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.VenueRepositoryPort;
import com.catalogo.catalogo_eventos.infrastructure.exception.NotFoundException;

@Service
public class UpdateVenueUseCaseImpl implements UpdateVenueUseCase {

    private final VenueRepositoryPort repository;

    public UpdateVenueUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Venue update(Long id, Venue venue) {
        Venue existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venue not found with id: " + id));

        existing.setName(venue.getName());
        existing.setAddress(venue.getAddress());

        return repository.save(existing);
    }
}

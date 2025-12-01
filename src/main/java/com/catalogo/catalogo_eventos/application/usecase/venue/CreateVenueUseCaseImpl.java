package com.catalogo.catalogo_eventos.application.usecase.venue;

import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.exception.BusinessRuleException;
import com.catalogo.catalogo_eventos.domain.model.Venue;
import com.catalogo.catalogo_eventos.domain.ports.in.CreateVenueUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.VenueRepositoryPort;

@Service
public class CreateVenueUseCaseImpl implements CreateVenueUseCase {

    private final VenueRepositoryPort repository;

    public CreateVenueUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Venue create(Venue venue) {
        if (venue.getName() == null || venue.getName().trim().isEmpty()) {
            throw new BusinessRuleException("Venue name must not be empty");
        }
        if (repository.existsByName(venue.getName())) {
            throw new BusinessRuleException("Venue with the same name already exists");
        }
        return repository.save(venue);
    }
}

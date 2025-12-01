package com.catalogo.catalogo_eventos.application.usecase.venue;

import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.ports.in.DeleteVenueUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.VenueRepositoryPort;
import com.catalogo.catalogo_eventos.infrastructure.exception.NotFoundException;

@Service
public class DeleteVenueUseCaseImpl implements DeleteVenueUseCase {

    private final VenueRepositoryPort repository;

    public DeleteVenueUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new NotFoundException("Venue not found with id: " + id);
        }
        repository.deleteById(id);
    }
}

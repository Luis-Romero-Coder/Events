package com.catalogo.catalogo_eventos.domain.ports.in;

import com.catalogo.catalogo_eventos.domain.model.Venue;

public interface UpdateVenueUseCase {
    Venue update(Long id, Venue venue);
}

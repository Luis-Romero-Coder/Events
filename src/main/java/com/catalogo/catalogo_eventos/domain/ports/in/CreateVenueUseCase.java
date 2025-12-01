package com.catalogo.catalogo_eventos.domain.ports.in;

import com.catalogo.catalogo_eventos.domain.model.Venue;

public interface CreateVenueUseCase {
    Venue create(Venue venue);
}

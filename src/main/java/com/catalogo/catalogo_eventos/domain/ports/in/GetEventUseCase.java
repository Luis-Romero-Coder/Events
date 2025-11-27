package com.catalogo.catalogo_eventos.domain.ports.in;

import com.catalogo.catalogo_eventos.domain.model.Event;

public interface GetEventUseCase {
    Event getById(Long id);
}

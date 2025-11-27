package com.catalogo.catalogo_eventos.domain.ports.in;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.catalogo.catalogo_eventos.domain.model.Event;

public interface ListEventUseCase {
    Page<Event> list(String nameFilter, Pageable pageable);
}

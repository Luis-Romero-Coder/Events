package com.catalogo.catalogo_eventos.domain.ports.in;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.model.PageRequests;
import com.catalogo.catalogo_eventos.domain.model.PaginatedResult;

public interface ListEventsUseCase {
    PaginatedResult<Event> list(String nameFilter, PageRequests pageRequest);
}

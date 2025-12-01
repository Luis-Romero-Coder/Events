package com.catalogo.catalogo_eventos.domain.ports.in;

import com.catalogo.catalogo_eventos.domain.model.PageRequests;
import com.catalogo.catalogo_eventos.domain.model.PaginatedResult;
import com.catalogo.catalogo_eventos.domain.model.Venue;

public interface ListVenuesUseCase {
    PaginatedResult<Venue> list(String nameFilter, PageRequests pageRequest);
}

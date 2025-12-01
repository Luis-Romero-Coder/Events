package com.catalogo.catalogo_eventos.infrastructure.adapters.in.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.catalogo_eventos.domain.model.PageRequests;
import com.catalogo.catalogo_eventos.domain.model.PaginatedResult;
import com.catalogo.catalogo_eventos.domain.model.Venue;
import com.catalogo.catalogo_eventos.domain.ports.in.CreateVenueUseCase;
import com.catalogo.catalogo_eventos.domain.ports.in.DeleteVenueUseCase;
import com.catalogo.catalogo_eventos.domain.ports.in.GetVenueUseCase;
import com.catalogo.catalogo_eventos.domain.ports.in.ListVenuesUseCase;
import com.catalogo.catalogo_eventos.domain.ports.in.UpdateVenueUseCase;
import com.catalogo.catalogo_eventos.infrastructure.adapters.in.web.dto.VenueRequest;
import com.catalogo.catalogo_eventos.infrastructure.adapters.in.web.dto.VenueResponse;
import com.catalogo.catalogo_eventos.infrastructure.mapper.VenueMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/venues")
public class VenueRestAdapter {

    private final CreateVenueUseCase createUseCase;
    private final GetVenueUseCase getUseCase;
    private final UpdateVenueUseCase updateUseCase;
    private final DeleteVenueUseCase deleteUseCase;
    private final ListVenuesUseCase listUseCase;
    private final VenueMapper mapper;

    public VenueRestAdapter(CreateVenueUseCase createUseCase,
                            GetVenueUseCase getUseCase,
                            UpdateVenueUseCase updateUseCase,
                            DeleteVenueUseCase deleteUseCase,
                            ListVenuesUseCase listUseCase,
                            VenueMapper mapper) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.listUseCase = listUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<VenueResponse> create(@Valid @RequestBody VenueRequest request) {
        Venue domain = mapper.toDomain(request);
        Venue created = createUseCase.create(domain);
        return ResponseEntity.status(201).body(mapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> getById(@PathVariable Long id) {
        Venue v = getUseCase.getById(id);
        return ResponseEntity.ok(mapper.toResponse(v));
    }

    @GetMapping
    public ResponseEntity<PaginatedResult<VenueResponse>> list(
            @RequestParam(value = "name", required = false) String name,
            Pageable pageable) {

        PageRequests pageRequest = new PageRequests(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString());
        PaginatedResult<Venue> domainPage = listUseCase.list(name, pageRequest);

        List<VenueResponse> content = domainPage.getContent().stream().map(mapper::toResponse).collect(Collectors.toList());

        PaginatedResult<VenueResponse> responsePage = new PaginatedResult<>(
                content,
                domainPage.getTotalElements(),
                domainPage.getTotalPages(),
                domainPage.getPageNumber(),
                domainPage.getPageSize()
        );

        return ResponseEntity.ok(responsePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueResponse> update(@PathVariable Long id, @Valid @RequestBody VenueRequest request) {
        Venue domain = mapper.toDomain(request);
        Venue updated = updateUseCase.update(id, domain);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}

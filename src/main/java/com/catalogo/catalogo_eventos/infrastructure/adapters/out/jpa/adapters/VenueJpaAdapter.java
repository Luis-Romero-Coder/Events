package com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.catalogo.catalogo_eventos.domain.model.PageRequests;
import com.catalogo.catalogo_eventos.domain.model.PaginatedResult;
import com.catalogo.catalogo_eventos.domain.model.Venue;
import com.catalogo.catalogo_eventos.domain.ports.out.VenueRepositoryPort;
import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.entity.VenueEntity;
import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.repository.VenueJpaRepository;
import com.catalogo.catalogo_eventos.infrastructure.mapper.VenueMapper;

@Component
public class VenueJpaAdapter implements VenueRepositoryPort {

    private final VenueJpaRepository repository;
    private final VenueMapper mapper;

    public VenueJpaAdapter(VenueJpaRepository repository, VenueMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Venue save(Venue venue) {
        VenueEntity entity = mapper.toEntity(venue);
        VenueEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public java.util.Optional<Venue> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public PaginatedResult<Venue> findByNameContaining(String name, PageRequests pageRequest) {
        int page = Math.max(0, pageRequest.getPage());
        int size = Math.max(1, pageRequest.getSize());
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size);

        Page<VenueEntity> pageResult = repository.findByNameContainingIgnoreCase(name == null ? "" : name, pageable);

        List<Venue> content = pageResult.getContent().stream().map(mapper::toDomain).collect(Collectors.toList());

        return new PaginatedResult<>(
                content,
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.getNumber(),
                pageResult.getSize()
        );
    }
}

package com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.model.PageRequests;
import com.catalogo.catalogo_eventos.domain.model.PaginatedResult;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;
import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.entity.EventEntity;
import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.repository.EventJpaRepository;
import com.catalogo.catalogo_eventos.infrastructure.mapper.EventMapper;

@Component
public class EventJpaAdapter implements EventRepositoryPort {

    private final EventJpaRepository jpaRepository;
    private final EventMapper mapper;

    public EventJpaAdapter(EventJpaRepository jpaRepository, EventMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Event save(Event event) {
        EventEntity entity = mapper.toEntity(event);
        EventEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public java.util.Optional<Event> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public PaginatedResult<Event> findByNameContaining(String name, PageRequests pageRequest) {
        // Convert domain PageRequest to Spring Pageable
        int page = Math.max(0, pageRequest.getPage());
        int size = Math.max(1, pageRequest.getSize());
        Pageable pageable = PageRequest.of(page, size);
        Page<EventEntity> pageResult = jpaRepository.findByNameContainingIgnoreCase(name == null ? "" : name, pageable);

        List<Event> content = pageResult.getContent()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());

        return new PaginatedResult<>(
                content,
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.getNumber(),
                pageResult.getSize()
        );
    }
}

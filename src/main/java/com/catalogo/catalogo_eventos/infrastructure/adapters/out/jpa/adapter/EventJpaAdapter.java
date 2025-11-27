package com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.adapter;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;
import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.entity.EventEntity;
import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.repository.EventJpaRepository;
import com.catalogo.catalogo_eventos.infrastructure.mapper.EventMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class EventJpaAdapter implements EventRepositoryPort {
    private final EventJpaRepository repository;
    private final EventMapper mapper;

    public EventJpaAdapter (EventJpaRepository repository, EventMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Event save(Event event){
        EventEntity entity= mapper.toEntity(event);
        entity = repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Event> findById(Long id){
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    @Override
    public  boolean existsByName(String name){
            return repository.existsByName(name);
    }
    

    @Override
    public Page<Event> findAllByNameContaining(String name, Pageable pageable){
        return repository.findAllByNameContainingIgnoreCase(name, pageable).map(mapper::toDomain);
    }
}

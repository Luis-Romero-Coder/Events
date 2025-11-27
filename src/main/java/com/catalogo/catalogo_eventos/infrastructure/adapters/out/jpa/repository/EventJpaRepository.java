package com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.entity.EventEntity;

public interface EventJpaRepository extends JpaRepository<EventEntity, Long >{
    boolean existsByName(String name);
    Page<EventEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}

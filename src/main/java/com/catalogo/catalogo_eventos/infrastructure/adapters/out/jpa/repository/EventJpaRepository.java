package com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.entity.EventEntity;

public interface EventJpaRepository extends JpaRepository<EventEntity, Long> {

    Optional<EventEntity> findByName(String name);

    boolean existsByName(String name);

    Page<EventEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

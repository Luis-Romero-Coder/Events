package com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogo.catalogo_eventos.infrastructure.adapters.out.jpa.entity.VenueEntity;

public interface VenueJpaRepository extends JpaRepository<VenueEntity, Long> {

    boolean existsByName(String name);

    Page<VenueEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

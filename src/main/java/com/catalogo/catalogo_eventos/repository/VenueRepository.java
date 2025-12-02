package com.catalogo.catalogo_eventos.repository;

import com.catalogo.catalogo_eventos.model.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<VenueEntity, Long> {
}

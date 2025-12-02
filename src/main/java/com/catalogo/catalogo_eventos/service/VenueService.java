package com.catalogo.catalogo_eventos.service;

import com.catalogo.catalogo_eventos.exception.NotFoundException;
import com.catalogo.catalogo_eventos.model.VenueEntity;
import com.catalogo.catalogo_eventos.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public List<VenueEntity> findAll() {
        return venueRepository.findAll();
    }

    public VenueEntity findById(Long id) {
        return venueRepository.findById(id).orElseThrow(() -> new NotFoundException("Venue no encontrado con id " + id));
    }

    public VenueEntity create(VenueEntity venue) {
        return venueRepository.save(venue);
    }

    public VenueEntity update(Long id, VenueEntity updated) {
        VenueEntity existing = findById(id);
        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        return venueRepository.save(existing);
    }

    public void delete(Long id) {
        VenueEntity existing = findById(id);
        venueRepository.delete(existing);
    }
}

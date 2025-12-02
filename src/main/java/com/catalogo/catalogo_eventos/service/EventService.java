package com.catalogo.catalogo_eventos.service;

import com.catalogo.catalogo_eventos.exception.ConflictException;
import com.catalogo.catalogo_eventos.exception.NotFoundException;
import com.catalogo.catalogo_eventos.model.EventEntity;
import com.catalogo.catalogo_eventos.model.VenueEntity;
import com.catalogo.catalogo_eventos.repository.EventRepository;
import com.catalogo.catalogo_eventos.repository.VenueRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public EventService(EventRepository eventRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }


    public Page<EventEntity> list(String nameFilter, Pageable pageable) {
        Specification<EventEntity> spec = Specification.where(null);

        if (nameFilter != null && !nameFilter.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + nameFilter.toLowerCase() + "%"));
        }

        return eventRepository.findAll(spec, pageable);
    }

    public EventEntity findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Evento no encontrado con id " + id));
    }

    public EventEntity create(EventEntity event) {
  
        Optional<EventEntity> existing = eventRepository.findByName(event.getName());
        if (existing.isPresent()) {
            throw new ConflictException("Ya existe un evento con el mismo nombre");
        }


        if (event.getVenue() != null && event.getVenue().getId() != null) {
            VenueEntity venue = venueRepository.findById(event.getVenue().getId())
                    .orElseThrow(() -> new NotFoundException("Venue no encontrado con id " + event.getVenue().getId()));
            event.setVenue(venue);
        }

        return eventRepository.save(event);
    }

    public EventEntity update(Long id, EventEntity updated) {
        EventEntity existing = findById(id);

        if (updated.getName() != null && !updated.getName().equalsIgnoreCase(existing.getName())) {
            Optional<EventEntity> conflict = eventRepository.findByName(updated.getName());
            if (conflict.isPresent() && !conflict.get().getId().equals(id)) {
                throw new ConflictException("Ya existe un evento con el mismo nombre");
            }
            existing.setName(updated.getName());
        }

        if (updated.getDescription() != null) existing.setDescription(updated.getDescription());
        if (updated.getDate() != null) existing.setDate(updated.getDate());

        if (updated.getVenue() != null) {
            if (updated.getVenue().getId() != null) {
                VenueEntity venue = venueRepository.findById(updated.getVenue().getId())
                        .orElseThrow(() -> new NotFoundException("Venue no encontrado con id " + updated.getVenue().getId()));
                existing.setVenue(venue);
            } else {
                existing.setVenue(updated.getVenue());
            }
        }

        return eventRepository.save(existing);
    }

    public void delete(Long id) {
        EventEntity existing = findById(id);
        eventRepository.delete(existing);
    }
}

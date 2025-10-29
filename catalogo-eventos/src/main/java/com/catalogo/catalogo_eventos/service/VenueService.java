package com.catalogo.catalogo_eventos.service;

import org.springframework.stereotype.Service;
import com.catalogo.catalogo_eventos.model.Venue;
import com.catalogo.catalogo_eventos.repository.VenueRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository){
        this.venueRepository = venueRepository;
    }

    public List<Venue> findAllVenues(){
        return venueRepository.findAll();
    }

    public Optional<Venue> getVenueById(String id){
        return venueRepository.findById(id);
    }

    public Venue createVenue(Venue venue){
        return venueRepository.save(venue);
    }

    public void deleteVenueById(String id){
        venueRepository.deleteById(id);
    }
}

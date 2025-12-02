package com.catalogo.catalogo_eventos.repository;

import com.catalogo.catalogo_eventos.model.Venue;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class VenueRepository {
    private final Map<String, Venue> veneuStrorage = new HashMap<>();

    public List<Venue> findAll(){
        return new ArrayList<>(veneuStrorage.values());
    }

    public Optional<Venue> findById(String id){
        return Optional.ofNullable(veneuStrorage.get(id));
    }

    public Venue save(Venue venue){
        veneuStrorage.put(venue.getId(), venue);
        return venue;
    }

    public void deleteById(String id){
        veneuStrorage.remove(id);
    }
}

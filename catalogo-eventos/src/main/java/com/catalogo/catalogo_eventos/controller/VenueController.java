package com.catalogo.catalogo_eventos.controller;

import com.catalogo.catalogo_eventos.model.Venue;
import com.catalogo.catalogo_eventos.service.VenueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService){
        this.venueService = venueService;
    }

    @GetMapping
    public List<Venue> findAllVenues(){
        return venueService.findAllVenues();
    }

    @GetMapping("/{id}")
    public Optional<Venue> getVenueById(@PathVariable String id) {
        return venueService.getVenueById(id);
    }
    
    @PostMapping
    public Venue createVenue(@RequestBody Venue venue){
        return venueService.createVenue(venue);
    }

    @DeleteMapping("/{id}")
    public void deleteVeneu(@PathVariable String id){
        venueService.deleteVenueById(id);
    }
}

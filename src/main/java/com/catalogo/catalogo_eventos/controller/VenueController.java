package com.catalogo.catalogo_eventos.controller;

import com.catalogo.catalogo_eventos.model.VenueEntity;
import com.catalogo.catalogo_eventos.service.VenueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<List<VenueEntity>> list() {
        return ResponseEntity.ok(venueService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(venueService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VenueEntity> create(@Valid @RequestBody VenueEntity venue) {
        VenueEntity created = venueService.create(venue);
        return ResponseEntity.created(URI.create("/api/venues/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueEntity> update(@PathVariable Long id, @Valid @RequestBody VenueEntity venue) {
        return ResponseEntity.ok(venueService.update(id, venue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        venueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
